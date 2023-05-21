document.addEventListener('DOMContentLoaded', function() {
    const customCursor = document.getElementById('custom-cursor');

    document.addEventListener('mousemove', function(e) {
        const x = e.clientX;
        const y = e.clientY;

        customCursor.style.transform = `translate(${x}px, ${y}px)`; // 커서 이미지를 마우스 위치로 이동
    });
});