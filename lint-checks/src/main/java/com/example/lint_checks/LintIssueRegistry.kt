package com.example.lint_checks // 반드시 실제 폴더 위치와 일치해야 함

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class LintIssueRegistry : IssueRegistry() {

    // 'api' overrides nothing 에러 해결: get()을 사용하거나 타입을 명시
    override val api: Int get() = CURRENT_API

    // issues 리스트 정의 (에러 방지를 위해 일단 빈 리스트로 설정)
    // 나중에 커스텀 Issue를 만드시면 여기에 추가하세요.
    override val issues: List<Issue> = listOf()

    // 최신 버전에서 권장되는 vendor 정보
    override val vendor: Vendor = Vendor(
        vendorName = "Release-Diary",
        feedbackUrl = "https://github.com/khw3991/Release-Diary/issues",
        contact = "https://github.com/khw3991/Release-Diary"
    )
}