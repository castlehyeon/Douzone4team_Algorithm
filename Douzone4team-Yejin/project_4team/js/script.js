const musicWrap = document.querySelector(".music_container");
const musicAudio = musicWrap.querySelector("#main-audio"); // audio

// music info
const imgWrap = musicWrap.querySelector(".page_two__main__current_music_photo");
const albumArt = musicWrap.querySelector(
  ".page_two__main__current_music_photo>img"
);
const musicName = musicWrap.querySelector(".currrent_music_info__title");
const musicArtist = musicWrap.querySelector(".currrent_music_info__singer");
const musicBottomBarName = musicWrap.querySelector(".playing__singer");
const musicBottomBarArtist = musicWrap.querySelector(".playing__title");

// controller btn
const playBtn = musicWrap.querySelector("#play-btn");
const prevBtn = musicWrap.querySelector("#prev-btn");
const nextBtn = musicWrap.querySelector("#next-btn");
const repeatBtn = musicWrap.querySelector("#repeat-btn");
const shuffleBtn = musicWrap.querySelector("#shuffle-btn");
const prevBottomBtn = musicWrap.querySelector("#prev-bottom-btn");
const playBottomBtn = musicWrap.querySelector("#play-bottom-btn");
const nextBottomBtn = musicWrap.querySelector("#next-bottom-btn");

// controller btn setarttribute 지정
repeatBtn.setAttribute("title", "repeat");
shuffleBtn.setAttribute("title", "shuffle");

// bar
const progress = musicWrap.querySelector(".m-progress");
const progressBar = progress.querySelector(".page_two__main__time_bar");
const progressPin = progress.querySelector(".page_two__main__time_bar>.pin");
const playTime = progress.querySelector(".current");
const totTime = progress.querySelector(".duration");

let list_index = 0; //musicList[0] ~ musicList[5] 순환 호출
musicAudio.volume = 0.5;

//음악 및 음악 정보 불러오기(music_list.js 활용)
const loadMusic = (num) => {
  albumArt.src = musicList[num].img;
  musicAudio.src = musicList[num].audio;
  musicName.innerHTML = musicList[num].name;
  musicArtist.innerHTML = musicList[num].artist;
  musicBottomBarName.innerHTML = musicList[num].name;
  musicBottomBarArtist.innerHTML = musicList[num].artist;
  imgWrap.style.transform = "scale(1)";
};

//음악 재생
const playMusic = () => {
  playBtn.value = "pause";
  playBottomBtn.value = "pause";
  playBtn.className = "fa-sharp fa-solid fa-circle-pause fa-3x";
  playBottomBtn.className = "fas fa-pause";
  let playPromise = musicAudio.play();
  if (playPromise !== undefined) {
    playPromise.then((_) => {}).catch((error) => {});
  }
};

//음악 일시정지
const pauseMusic = () => {
  playBtn.value = "play_arrow";
  playBottomBtn.value = "play_arrow";
  playBtn.className = "fas fa-play-circle fa-3x"; // 플레이 아이콘
  playBottomBtn.className = "fa-solid fa-play";
  musicAudio.pause();
};

//재생(일시정지)버튼 클릭 시
playBtn.addEventListener("click", (e) => {
  let getText = playBtn.value;
  getText == "pause" ? pauseMusic() : playMusic();
});

//재생(일시정지)버튼 클릭 시
playBottomBtn.addEventListener("click", (e) => {
  let getText = playBottomBtn.value;
  getText == "pause" ? pauseMusic() : playMusic();
});

//이전 곡 버튼 클릭 시
const prevMusic = () => {
  list_index--;
  if (list_index < 0) {
    list_index = musicList.length - 1;
  }
  loadMusic(list_index);
  playMusic();
  playListMusic();
};

//다음 곡 버튼 클릭 시
const nextMusic = () => {
  list_index++;
  list_index = list_index % musicList.length;
  loadMusic(list_index);
  playMusic();
  playListMusic();
};

nextBtn.addEventListener("click", () => {
  nextMusic();
});
prevBtn.addEventListener("click", () => {
  prevMusic();
});

nextBottomBtn.addEventListener("click", () => {
  nextMusic();
});
prevBottomBtn.addEventListener("click", () => {
  prevMusic();
});

//재생시간, 전체시간 표시 및 재생바
musicAudio.addEventListener("timeupdate", (e) => {
  let curr = e.target.currentTime;
  let duration = e.target.duration;
  let progressRatio = (curr / duration) * 100;
  progressBar.style.width = `${progressRatio}%`;
  progressPin.style.left = `${96.5}%`;
  //재생시간 표시
  let currMin = Math.floor(curr / 60);
  let currSec = Math.floor(curr % 60);
  if (currSec < 10) currSec = `0${currSec}`;
  playTime.innerHTML = `${currMin}:${currSec}`;
  musicAudio.addEventListener("loadeddata", () => {
    //전체시간 표시
    let totDuration = musicAudio.duration;
    let totMin = Math.floor(totDuration / 60);
    let totSec = Math.floor(totDuration % 60);
    if (totSec < 10) totSec = `0${totSec}`; //1~9초는 01~09초로 표기
    totTime.innerHTML = `${totMin}:${totSec}`;
  });
});

//재생 바 특정 위치 클릭 시
progress.addEventListener("click", (e) => {
  let maxWidth = progress.clientWidth;
  let clickXpos = e.offsetX;
  let totDuration = musicAudio.duration;
  musicAudio.currentTime = (clickXpos / maxWidth) * totDuration;
  playMusic();
  progressPin.style.left = `${96.5}%`;
});

repeatBtn.addEventListener("click", () => {
  let getTextRepeat = repeatBtn.innerHTML;
  switch (getTextRepeat) {
    case "repeat":
      repeatBtn.innerHTML = "repeat_one";
      repeatBtn.style.color = "black";
      repeatBtn.setAttribute("title", "repeat_one");
      break;
    case "repeat_one":
      repeatBtn.innerHTML = "repeat";
      repeatBtn.style.color = "gray";
      repeatBtn.setAttribute("title", "repeat");
      break;
  }
});

//셔플버튼 클릭 시
shuffleBtn.addEventListener("click", () => {
  let getTextShuffle = shuffleBtn.title;
  switch (getTextShuffle) {
    case "shuffle":
      shuffleBtn.title = "shuffle_on";
      shuffleBtn.style.color = "black";
      shuffleBtn.setAttribute("title", "shuffle_on");
      break;
    case "shuffle_on":
      shuffleBtn.title = "shuffle";
      shuffleBtn.style.color = "gray";
      shuffleBtn.setAttribute("title", "shuffle");
      break;
  }
});

//음악 재생 끝날 때
musicAudio.addEventListener("ended", () => {
  let getTextRepeat = repeatBtn.title;
  let getTextShuffle = shuffleBtn.title;
  switch (getTextShuffle) {
    case "shuffle":
      loadMusic(list_index);
      playMusic();
      break;
    case "shuffle_on":
      if (getTextRepeat == "repeat") {
        let randomIndex = Math.floor(Math.random() * musicList.length);
        do {
          randomIndex = Math.floor(Math.random() * musicList.length);
        } while (list_index == randomIndex);
        list_index = randomIndex;
        loadMusic(list_index);
        playMusic();
      }
      break;
  }
  switch (getTextRepeat) {
    case "repeat":
      nextMusic();
      break;
    case "repeat_one":
      loadMusic(list_index);
      playMusic();
      break;
  }
  playListMusic();
});

window.addEventListener("load", () => {
  loadMusic(list_index);
  playListMusic();
});
