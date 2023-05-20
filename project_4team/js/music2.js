const playBtn = document.getElementById("play");
const musicContainer = document.getElementById("musicContainer");
const audio = document.getElementById("audio");
const prevBtn = document.getElementById("prev");
const nextBtn = document.getElementById("next");
const progress = document.getElementById("progress");
const progressContainer = document.getElementById('progress-container');
const imgCover = document.getElementById("cover");
const title = document.getElementById("title");

const songs = [
  {
      link: "https://movie4team.s3.ap-northeast-2.amazonaws.com/Howls+Moving+Castle+OST++Theme+Song.mp3",
      key: "하울"
  },
  {
      link: "https://movie4team.s3.ap-northeast-2.amazonaws.com/%E1%84%87%E1%85%A7%E1%84%85%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%B1%E1%84%8B%E1%85%B4%E1%84%91%E1%85%A9%E1%84%82%E1%85%AD.mp3",
      key: "포뇨"
  },
  {
      link: "https://movie4team.s3.ap-northeast-2.amazonaws.com/Always+With+Me.mp3",
      key: "치히로"
  },
  {
      link: "https://movie4team.s3.ap-northeast-2.amazonaws.com/%E1%84%85%E1%85%A1%E1%84%91%E1%85%B2%E1%84%90%E1%85%A1.mp3",
      key: "라퓨타"
  },
  {
      link: "https://movie4team.s3.ap-northeast-2.amazonaws.com/%E1%84%86%E1%85%A1%E1%84%82%E1%85%A7%E1%84%87%E1%85%A2%E1%84%83%E1%85%A1%E1%86%AF%E1%84%87%E1%85%AE%E1%84%8F%E1%85%B5%E1%84%8F%E1%85%B5.mp3",
      key: "마녀배달부"
  }
];


let songIndex = 2;

loadSong(songs[songIndex]);

function loadSong(song) {
  title.innerText = song.key; // 제목
  audio.src = song.link;      // 주소
  imgCover.src = "./images/Player.gif";
}

function playMusic() {
  musicContainer.classList.add("play");
  playBtn.innerHTML = `<i class="fa-solid fa-pause"></i>`;

  audio.play();
}

function pauseMusic(){
    musicContainer.classList.remove('play');
    playBtn.innerHTML = `<i class="fa-solid fa-play"></i>`;

    audio.pause();
}

function playPrevSong() {
    songIndex--;

    if (songIndex < 0) {
      songIndex = songs.length - 1;
    }
  
    loadSong(songs[songIndex]);
    playMusic();
}

function playNextSong (){
    songIndex++;

    if(songIndex > 2){
        songIndex = 0;
    }
    loadSong(songs[songIndex]);
    playMusic();
}

function updateProgress(e){
    const {duration, currentTime} = e.srcElement;
    const progressPer = (currentTime / duration) * 100;
    progress.style.width = `${progressPer}%`;
}

function changeProgress(e){

    const width = e.target.clientWidth; // 전체 넓이
    const offsetX = e.offsetX; // 현재 x 좌표;
    const duration = audio.duration; // 재생길이
    audio.currentTime = (offsetX / width) * duration; 
}

playBtn.addEventListener("click", () => {
    const isPlaying = musicContainer.classList.contains('play');

    if(isPlaying){
        pauseMusic();
    } else{
        playMusic();
    }
});

prevBtn.addEventListener("click", playPrevSong);
nextBtn.addEventListener('click', playNextSong);
audio.addEventListener('ended', playNextSong);
audio.addEventListener('timeupdate', updateProgress);

progressContainer.addEventListener('click', changeProgress);