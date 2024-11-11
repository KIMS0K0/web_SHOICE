function toggleMenu() {
    const menu = document.getElementById('dropdownMenu');
    menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
}

function share() {
		var url = '';
		var textarea = document.createElement("textarea");
		document.body.appendChild(textarea);
		url = window.document.location.href;
		textarea.value = url;
		textarea.select();
		document.execCommand("copy");
		document.body.removeChild(textarea);
		alert("URL이 복사되었습니다.");
}

function update(board_seq) {
    location.href = `/board/boardUpdate?board_seq=${board_seq}`;
}

function remove(board_seq) {
    if (confirm('삭제하시겠습니까?')) {
        location.href = `/board/boardDelete?board_seq=${board_seq}`;
    }
}

function commentDelete(board_seq, comment_seq) {
    if (confirm('삭제하시겠습니까?')) {
        location.href=`/comment/deleteComment?board_seq=${board_seq}&comment_seq=${comment_seq}`;
    }
}

function replyDelete(board_seq, reply_seq) {
	if (confirm('삭제하시겠습니까?')) {
        location.href=`/comment/deleteReply?board_seq=${board_seq}&reply_seq=${reply_seq}`;
    }
}
function toggleReplyInput(button) {
    const replyInput = button.closest('.comment-content').querySelector('.reply-input');
    replyInput.style.display = replyInput.style.display === 'none' ? 'block' : 'none';
}
function toggleComments() {
    const commentsSection = document.getElementById('comments-section');
    const chevronIcon = document.getElementById('chevronIcon');

    if (commentsSection.style.display === 'none' || commentsSection.style.display === '') {
        commentsSection.style.display = 'block'; // 댓글 섹션 보이기
        chevronIcon.classList.remove('fa-chevron-down');
        chevronIcon.classList.add('fa-chevron-up'); // 아이콘을 위쪽 화살표로 변경
    } else {
        commentsSection.style.display = 'none'; // 댓글 섹션 숨기기
        chevronIcon.classList.remove('fa-chevron-up');
        chevronIcon.classList.add('fa-chevron-down'); // 아이콘을 아래쪽 화살표로 변경
    }
}

