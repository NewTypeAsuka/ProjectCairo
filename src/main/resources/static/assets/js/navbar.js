/**
 * /js/navbar.js
 * - navbar collapse 열림/닫힘에 따라 아이콘 변경
 *   닫힘: bi-list
 *   열림: bi-chevron-up
 */

(() => {
    "use strict";

    const NAVBAR_MENU_ID = "navbarMenu";
    const MENU_ICON_ID = "menuIcon";

    const setIcon = (iconEl, isOpen) => {
        // Bootstrap Icons 클래스 토글
        iconEl.classList.toggle("bi-list", !isOpen);
        iconEl.classList.toggle("bi-chevron-up", isOpen);
    };

    const initNavbarIconToggle = () => {
        const menuEl = document.getElementById(NAVBAR_MENU_ID);
        const iconEl = document.getElementById(MENU_ICON_ID);

        if (!menuEl || !iconEl) return;

        // 초기 상태 반영(혹시 로드 시 이미 열려있는 경우)
        setIcon(iconEl, menuEl.classList.contains("show"));

        // Bootstrap collapse 이벤트
        menuEl.addEventListener("shown.bs.collapse", () => {
            setIcon(iconEl, true);
        });

        menuEl.addEventListener("hidden.bs.collapse", () => {
            setIcon(iconEl, false);
        });
    };

    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", initNavbarIconToggle);
    } else {
        initNavbarIconToggle();
    }
})();
