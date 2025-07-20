package com.ss9.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SearchLogServiceImpl implements SearchLogService {

    private static final String LOG_FILE_PATH = "app.ss9";
    private static final Pattern SEARCH_PATTERN = Pattern.compile("Tìm kiếm với từ khóa: '(.*?)'");

    @Override
    public Map<String, Integer> getSearchKeywordStats() {
        Map<String, Integer> keywordCount = new HashMap<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(LOG_FILE_PATH));
            for (String line : lines) {
                Matcher matcher = SEARCH_PATTERN.matcher(line);
                if (matcher.find()) {
                    String keyword = matcher.group(1).toLowerCase();
                    keywordCount.put(keyword, keywordCount.getOrDefault(keyword, 0) + 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Không thể đọc file ss9", e);
        }

        return keywordCount;
    }

    @Override
    public List<String> getSearchKeywords() {
        return new ArrayList<>(getSearchKeywordStats().keySet());
    }
}
