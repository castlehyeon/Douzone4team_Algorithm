$(function() {
    // 플레이리스트 아이템 드래그 가능하게 설정
    $("#playlist li").draggable({
      helper: "clone",
      cursor: "move",
      zIndex: 9999,
      revert: "invalid"
    });
  
    // 비디오 플레이어 드랍 영역 드랍 가능하게 설정
    $("#player").droppable({
      drop: function(event, ui) {
        const videoUrl = $(ui.draggable).data("video");
        $("#player").html(`<video src="${videoUrl}" controls autoplay></video>`);
      }
    });
  });

  $(function() {
    // 플레이리스트 아이템 드래그 가능하게 설정
    $("#maplist li").draggable({
      helper: "clone",
      cursor: "move",
      zIndex: 9999,
      revert: "invalid"
    });
  
    // 비디오 플레이어 드랍 영역 드랍 가능하게 설정
    $("#map").droppable({
      drop: function(event, ui) {
        const address = $(ui.draggable).data("video");
        $("#map").html(`<video src="${videoUrl}" controls autoplay></video>`);
      }
    });
  });