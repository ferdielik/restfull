package com.ferdielik.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ferdielik.entity.Attachment;

/**
 * base64 file upload
 */

@Controller
@RequestMapping("/upload")
public class UploadController
{
    private static final String FILE_SAVE_PATH = "/opt/tmp/";

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity upload(@RequestBody Attachment attachment) throws Exception
    {
        writeToFileFromBytes(attachment);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    private void writeToFileFromBytes(Attachment attachment) throws Exception
    {
        byte[] bytes = Base64.getDecoder().decode(attachment.getStream());
        FileOutputStream fos = new FileOutputStream(buildSavePath(attachment));
        fos.write(bytes);
        fos.close();
    }

    private String buildSavePath(Attachment attachment)
    {
        return FILE_SAVE_PATH + attachment.getFileName() + attachment.getFileExtension();
    }
}
