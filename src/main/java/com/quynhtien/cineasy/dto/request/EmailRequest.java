package com.quynhtien.cineasy.dto.request;

import java.util.UUID;

public record EmailRequest (
    String recipient,
    UUID tokenId
) {}
