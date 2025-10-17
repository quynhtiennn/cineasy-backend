package com.quynhtien.cineasy.dto.response;

import org.springframework.core.io.Resource;

public record FileResponse(String contentType, Resource resource) {}
