/**
 * /js/app.js
 * 공통 초기화 진입점
 * - 페이지 전체에서 공통으로 쓸 초기화/유틸을 여기에 둔다.
 * - 각 기능별 파일(navbar.js 등)은 app.js 이후에 로드된다.
 */

(() => {
    "use strict";

    // 전역 네임스페이스(필요하면 확장)
    window.NTP = window.NTP || {};

    /**
     * DOM 준비 후 실행될 공통 초기화
     */
    const init = () => {
        // 지금은 공통 초기화가 크게 없으니 자리만 둔다.
        // 예) 추후:
        // - 다크모드 토글 로직 연결
        // - 공통 fetch 래퍼
        // - 공통 알림(toast) 핸들러
    };

    // DOMContentLoaded 보장
    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", init);
    } else {
        init();
    }
})();
