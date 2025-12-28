/**
 * DOM 로딩이 끝난 뒤 로그인 관련 이벤트를 초기화 -> 👉 DOM이 완전히 만들어지기 전에 요소를 찾거나 이벤트를 붙이면 실패하기 때문
 */
document.addEventListener("DOMContentLoaded", () => {

    // 로그인 form (<form id="loginForm">)
    const loginForm = document.getElementById("loginForm");

    // 모달 하단 안내 문구 영역
    const loginHint = document.getElementById("loginHint");

    // 로그인 모달 전체 엘리먼트
    const loginModalEl = document.getElementById("loginModal");

    // 필수 요소 중 하나라도 없으면 스크립트 실행 중단
    if (!loginForm || !loginHint || !loginModalEl) {
        return;
    }

    /**
     * 로그인 모달이 "열릴 때" 실행되는 이벤트
     * → 실패 메시지가 남아있지 않도록 기본 문구로 원복
     */
    loginModalEl.addEventListener("show.bs.modal", () => {
        loginHint.textContent = "블로그 주인장만 로그인할 수 있습니다";
        loginHint.classList.remove("text-danger");   // 실패 색상 제거
        loginHint.classList.add("text-secondary");   // 기본 안내 색상
    });

    /**
     * 로그인 form 제출 시 처리
     */
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault(); // 브라우저 기본 동작 차단 -> 페이지 이동 방지

        // form 데이터를 가져오기
        const formData = new FormData(loginForm);

        // 서버로 보낼 로그인 payload
        const payload = {
            userId: formData.get("userId"),
            userPw: formData.get("userPw"),
        };

        try {
            // 로그인 API 호출
            const res = await fetch("/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(payload),
            });

            /**
             * 로그인 성공 (HTTP 200)
             */
            if (res.ok) {
                // 로그인 모달 닫기
                const modal = bootstrap.Modal.getOrCreateInstance(loginModalEl);
                modal.hide();

                // 로그인 성공 후 상단 UI(로그인 버튼 → 로그아웃/관리 버튼) 갱신
// await refreshAuthArea(); // 추후 구현해야 함 -> 로그인하면 navbar도 바뀌는 로직 부분
                return;
            }

            /**
             * 로그인 실패 (401, 403 등)
             * → 모달은 그대로 두고 문구만 변경
             */
            loginHint.textContent = "아이디 또는 비밀번호가 올바르지 않습니다";
            loginHint.classList.remove("text-secondary");
            loginHint.classList.add("text-danger");

        } catch (err) {
            /**
             * 네트워크 오류 / 서버 장애 등 예외 상황
             * → 사용자 입장에서는 로그인 실패로 처리
             */
            loginHint.textContent = "서버와의 통신이 불가능합니다. 잠시 후 다시 시도해주세요";
            loginHint.classList.remove("text-secondary");
            loginHint.classList.add("text-danger");
        }
    });
});

/**
 * 로그인 성공 후 상단 navbar 인증 영역 갱신 함수
 *
 * - 서버에서 현재 로그인 상태를 조회 (/api/auth/me)
 * - 로그인 상태면:
 *   1) 로그인 버튼 제거
 *   2) 관리 / 로그아웃 버튼 추가
 */
async function refreshAuthArea() {

    // navbar 안의 인증 영역 (<ul id="authArea">)
    const authArea = document.getElementById("authArea");
    if (!authArea) return;

    try {
        // 현재 로그인 사용자 정보 조회
        const res = await fetch("/auth/me", { method: "GET" });
        if (!res.ok) return;

        const me = await res.json();

        /**
         * 로그인 상태일 경우 UI 변경
         */
        if (me && me.loggedIn) {

            // 기존 로그인 버튼(li)을 찾아 제거
            const loginBtnLi =
                authArea
                    .querySelector('button[data-bs-target="#loginModal"]')
                    ?.closest("li");

            if (loginBtnLi) loginBtnLi.remove();

            // 관리 / 로그아웃 버튼 추가
            const li = document.createElement("li");
            li.className = "nav-item";
            li.innerHTML = `
                <div class="d-flex gap-2">
                    <a class="btn btn-outline-secondary btn-sm" href="/admin">관리</a>
                    <button class="btn btn-outline-danger btn-sm" type="button" id="logoutBtn">
                        로그아웃
                    </button>
                </div>
            `;
            authArea.appendChild(li);

            /**
             * 로그아웃 버튼 클릭 처리
             */
            const logoutBtn = document.getElementById("logoutBtn");
            logoutBtn?.addEventListener("click", async () => {
                await fetch("/api/auth/logout", { method: "POST" });

                // 가장 단순한 처리: 새로고침으로 UI 초기화
                location.reload();
            });
        }
    } catch (e) {
        // 인증 영역 갱신 실패 시 아무 것도 하지 않음 (UX 깨짐 방지)
    }
}
