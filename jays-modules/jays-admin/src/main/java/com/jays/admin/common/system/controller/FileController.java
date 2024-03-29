package com.jays.admin.common.system.controller;

import cn.hutool.core.util.StrUtil;
import com.jays.admin.common.core.config.ConfigProperties;
import com.jays.admin.common.core.utils.SecurityUtils;
import com.jays.common.core.annotation.OperationLog;
import com.jays.common.core.utils.FileServerUtil;
import com.jays.common.core.web.ApiResult;
import com.jays.common.core.web.BaseController;
import com.jays.common.core.web.PageResult;
import com.jays.admin.common.system.entity.FileRecord;
import com.jays.admin.common.system.param.FileRecordParam;
import com.jays.admin.common.system.service.FileRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传下载控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:24
 */
@Tag(name = "文件上传下载")
@RestController
@RequestMapping("/file")
public class FileController extends BaseController {
    @Resource
    private ConfigProperties config;
    @Resource
    private FileRecordService fileRecordService;

    @PreAuthorize("hasAuthority('sys:file:upload')")
    @OperationLog
    @Operation(summary ="上传文件")
    @PostMapping("/upload")
    public ApiResult<FileRecord> upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        FileRecord result = null;
        try {
            String dir = getUploadDir();
            File upload = FileServerUtil.upload(file, dir, config.getUploadUuidName());
            String path = upload.getAbsolutePath().replace("\\", "/").substring(dir.length() - 1);
            String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/upload");
            String originalName = file.getOriginalFilename();
            result = new FileRecord();
            result.setCreateUserId(SecurityUtils.getLoginUserId());
            result.setName(StrUtil.isBlank(originalName) ? upload.getName() : originalName);
            result.setLength(upload.length());
            result.setPath(path);
            result.setUrl(requestURL + "/" + path);
            String contentType = FileServerUtil.getContentType(upload);
            result.setContentType(contentType);
            if (FileServerUtil.isImage(contentType)) {
                result.setThumbnail(requestURL + "/thumbnail/" + path);
            }
            result.setDownloadUrl(requestURL + "/download/" + path);
            fileRecordService.save(result);
            return success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("上传失败", result).setError(e.toString());
        }
    }

    @PreAuthorize("hasAuthority('sys:file:upload')")
    @OperationLog
    @Operation(summary ="上传base64文件")
    @Parameters({
            @Parameter(name = "base64", description = "base64", required = true),
            @Parameter(name = "fileName", description = "文件名称")
    })
    @PostMapping("/upload/base64")
    public ApiResult<FileRecord> uploadBase64(String base64, String fileName, HttpServletRequest request) {
        FileRecord result = null;
        try {
            String dir = getUploadDir();
            File upload = FileServerUtil.upload(base64, fileName, getUploadDir());
            String path = upload.getAbsolutePath().substring(dir.length()).replace("\\", "/");
            String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/upload/base64");
            result = new FileRecord();
            result.setCreateUserId(SecurityUtils.getLoginUserId());
            result.setName(StrUtil.isBlank(fileName) ? upload.getName() : fileName);
            result.setLength(upload.length());
            result.setPath(path);
            result.setUrl(requestURL + path);
            result.setThumbnail(FileServerUtil.isImage(upload) ? (requestURL + "/thumbnail" + path) : null);
            fileRecordService.save(result);
            return success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("上传失败", result).setError(e.toString());
        }
    }

    @Operation(summary ="查看原文件")
    @GetMapping("/{dir}/{name:.+}")
    public void preview(@PathVariable("dir") String dir, @PathVariable("name") String name,
                        HttpServletResponse response, HttpServletRequest request) {
        File file = new File(getUploadDir(), dir + "/" + name);
        FileServerUtil.preview(file, getPdfOutDir(), config.getOpenOfficeHome(), response, request);
    }

    @Operation(summary ="下载原文件")
    @GetMapping("/download/{dir}/{name:.+}")
    public void download(@PathVariable("dir") String dir, @PathVariable("name") String name,
                         HttpServletResponse response, HttpServletRequest request) {
        String path = dir + "/" + name;
        FileRecord record = fileRecordService.getByIdPath(path);
        File file = new File(getUploadDir(), path);
        String fileName = record == null ? file.getName() : record.getName();
        FileServerUtil.preview(file, true, fileName, null, null, response, request);
    }

    @Operation(summary ="查看缩略图")
    @GetMapping("/thumbnail/{dir}/{name:.+}")
    public void thumbnail(@PathVariable("dir") String dir, @PathVariable("name") String name,
                          HttpServletResponse response, HttpServletRequest request) {
        File file = new File(getUploadDir(), dir + "/" + name);
        File thumbnail = new File(getUploadSmDir(), dir + "/" + name);
        FileServerUtil.previewThumbnail(file, thumbnail, config.getThumbnailSize(), response, request);
    }

    @PreAuthorize("hasAuthority('sys:file:remove')")
    @OperationLog
    @Operation(summary ="删除文件")
    @DeleteMapping("/remove/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        FileRecord record = fileRecordService.getById(id);
        if (fileRecordService.removeById(id)) {
            if (StrUtil.isNotBlank(record.getPath())) {
                fileRecordService.deleteFileAsync(Arrays.asList(
                        new File(getUploadDir(), record.getPath()),
                        new File(getUploadSmDir(), record.getPath())
                ));
            }
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:file:remove')")
    @OperationLog
    @Operation(summary ="批量删除文件")
    @Parameters({
            @Parameter(name = "ids", description = "id数组", required = true)
    })
    @DeleteMapping("/remove/batch")
    public ApiResult<?> deleteBatch(@RequestBody List<Integer> ids) {
        List<FileRecord> fileRecords = fileRecordService.listByIds(ids);
        if (fileRecordService.removeByIds(ids)) {
            List<File> files = new ArrayList<>();
            for (FileRecord record : fileRecords) {
                if (StrUtil.isNotBlank(record.getPath())) {
                    files.add(new File(getUploadDir(), record.getPath()));
                    files.add(new File(getUploadSmDir(), record.getPath()));
                }
            }
            fileRecordService.deleteFileAsync(files);
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:file:list')")
    @OperationLog
    @Operation(summary ="分页查询文件")
    @GetMapping("/page")
    public ApiResult<PageResult<FileRecord>> page(FileRecordParam param, HttpServletRequest request) {
        PageResult<FileRecord> result = fileRecordService.pageRel(param);
        String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/page");
        for (FileRecord record : result.getList()) {
            if (StrUtil.isNotBlank(record.getPath())) {
                record.setUrl(requestURL + "/" + record.getPath());
                if (FileServerUtil.isImage(record.getContentType())) {
                    record.setThumbnail(requestURL + "/thumbnail/" + record.getPath());
                }
                record.setDownloadUrl(requestURL + "/download/" + record.getPath());
            }
        }
        return success(result);
    }

    @PreAuthorize("hasAuthority('sys:file:list')")
    @OperationLog
    @Operation(summary ="查询全部文件")
    @GetMapping("/list")
    public ApiResult<List<FileRecord>> list(FileRecordParam param, HttpServletRequest request) {
        List<FileRecord> records = fileRecordService.listRel(param);
        String requestURL = StrUtil.removeSuffix(request.getRequestURL(), "/list");
        for (FileRecord record : records) {
            if (StrUtil.isNotBlank(record.getPath())) {
                record.setUrl(requestURL + "/" + record.getPath());
                if (FileServerUtil.isImage(record.getContentType())) {
                    record.setThumbnail(requestURL + "/thumbnail/" + record.getPath());
                }
                record.setDownloadUrl(requestURL + "/download/" + record.getPath());
            }
        }
        return success(records);
    }

    /**
     * 文件上传基目录
     */
    private String getUploadBaseDir() {
        return File.listRoots()[config.getUploadLocation()].getAbsolutePath()
                .replace("\\", "/") + "/upload/";
    }

    /**
     * 文件上传位置
     */
    private String getUploadDir() {
        return getUploadBaseDir() + "file/";
    }

    /**
     * 缩略图生成位置
     */
    private String getUploadSmDir() {
        return getUploadBaseDir() + "thumbnail/";
    }

    /**
     * office转pdf输出位置
     */
    private String getPdfOutDir() {
        return getUploadBaseDir() + "pdf/";
    }

}
