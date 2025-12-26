// /src/main/resources/static/js/tagMaker.js
(() => {
    "use strict";

    const MAX_TAGS = 2;

    const els = {
        input: document.getElementById("postTags"),
        badgeArea: document.getElementById("tagBadgeArea"),
        hint: document.getElementById("tagHint"),
        hidden: document.getElementById("postTagsHidden"),
    };

    if (!els.input || !els.badgeArea || !els.hint || !els.hidden) {
        return;
    }

    let tags = []; // canonical: "태그명" (앞의 # 제거한 값)

    const setHint = (msg, isError = false) => {
        els.hint.textContent = msg;
        els.hint.classList.toggle("text-danger", isError);
        els.hint.classList.toggle("text-muted", !isError);
    };

    const normalize = (raw) => {
        let v = (raw ?? "").trim();

        // 공백/엔터로 들어오는 경우 대비
        v = v.replace(/\s+/g, "");

        // 맨 앞 # 제거
        if (v.startsWith("#")) v = v.slice(1);

        // 내부에 #가 또 섞이면 제거
        v = v.replace(/#/g, "");

        return v;
    };

    const syncHidden = () => {
        // 서버로 보내는 값 형식: "#태그1,#태그2"
        els.hidden.value = tags.map(t => `#${t}`).join(",");
    };

    const render = () => {
        els.badgeArea.innerHTML = "";

        tags.forEach((t) => {
            const badge = document.createElement("span");
            badge.className = "tag-badge";

            const text = document.createElement("span");
            text.className = "tag-text";
            text.textContent = `#${t}`;

            const btn = document.createElement("button");
            btn.type = "button";
            btn.className = "tag-remove";
            btn.setAttribute("aria-label", `태그 삭제: #${t}`);
            btn.innerHTML = "&times;";
            btn.addEventListener("click", () => {
                tags = tags.filter(x => x !== t);
                render();
                syncHidden();
                setHint(`최대 ${MAX_TAGS}개까지 입력 가능합니다`, false);
            });

            badge.appendChild(text);
            badge.appendChild(btn);
            els.badgeArea.appendChild(badge);
        });
    };

    const tryAddTag = (raw) => {
        const v = normalize(raw);

        if (!v) {
            setHint(`태그명을 입력해주세요. 예) #개발`, true);
            return;
        }

        if (tags.includes(v)) {
            setHint(`이미 추가된 태그입니다: #${v}`, true);
            return;
        }

        if (tags.length >= MAX_TAGS) {
            setHint(`태그는 최대 ${MAX_TAGS}개까지만 가능합니다`, true);
            return;
        }

        tags.push(v);
        els.input.value = "";
        render();
        syncHidden();

        const remain = MAX_TAGS - tags.length;
        if (remain > 0) {
            setHint(`추가로 ${remain}개 더 입력 가능합니다`, false);
        } else {
            setHint(`태그 ${MAX_TAGS}개 입력되었습니다`, false);
        }
    };

    // 스페이스/엔터 입력 시 태그 확정
    els.input.addEventListener("keydown", (e) => {
        if (e.key === "Enter" || e.key === " ") {
            e.preventDefault(); // 엔터 submit 방지, 스페이스 입력 방지
            tryAddTag(els.input.value);
        }

        // 백스페이스로 빠르게 마지막 태그 삭제(입력이 비어있을 때)
        if (e.key === "Backspace" && els.input.value.trim() === "" && tags.length > 0) {
            // UX 편의: 마지막 태그 삭제
            tags.pop();
            render();
            syncHidden();
            setHint(`최대 ${MAX_TAGS}개까지 입력 가능합니다`, false);
        }
    });

    // 초기 문구
    setHint(`최대 ${MAX_TAGS}개까지 입력 가능합니다`, false);
    render();
    syncHidden();
})();
