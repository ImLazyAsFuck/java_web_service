package com.ss9.service;

import java.util.List;
import java.util.Map;

public interface SearchLogService {
    Map<String, Integer> getSearchKeywordStats();
    List<String> getSearchKeywords();
}
