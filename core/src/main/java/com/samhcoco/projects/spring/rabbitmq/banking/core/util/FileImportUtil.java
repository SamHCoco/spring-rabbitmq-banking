package com.samhcoco.projects.spring.rabbitmq.banking.core.util;

import com.google.gson.Gson;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;

public abstract class FileImportUtil {

    public static final Gson gson = new Gson();

    /**
     * Converts a {@link Resource} file containing an array of JSON objects to a collection of POJOs.
     * @param resource {@link Resource}
     * @param clazz Array {@link Class} name.
     * @param <T> Object type.
     * @return Collection of <T>.
     * @throws IOException If exception occurred.
     */
    public static <T> List<T> importJson(Resource resource, Class<T[]> clazz) throws IOException {
        return Arrays.asList(gson.fromJson(readFileToString(resource.getFile(), UTF_8), clazz));
    }

}
