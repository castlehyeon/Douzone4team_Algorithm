let All_song = [
  {
    name: "Unforgiven",
    path: "music/1.mp3",
    img: "images/1.jpg",
    singer: "LE SSERAFIM"
  },
  {
    name: "Hype Boy",
    path: "music/2.mp3",
    img: "images/2.jpg",
    singer: "New Zeans"
  },
  {
    name: "I AM",
    path: "music/3.mp3",
    img: "images/3.jpg",
    singer: "IVE"
  },
  {
    name: "Flower",
    path: "music/4.mp3",
    img: "images/4.jpg",
    singer: "지수"
  },
  {
    name: "Antifreeze",
    path: "music/5.mp3",
    img: "images/5.jpg",
    singer: "백예린"
  },
  {
    name: "Cupid",
    path: "music/6.mp3",
    img: "images/6.jpg",
    singer: "FIFTY FIFTY"
  },
  {
    name: "OMG",
    path: "music/7.mp3",
    img: "images/7.jpg",
    singer: "New Jeans"
  }
];

new Swiper('.swiper', {
  loop: true,
  slidesPerView: 3.5, //
  spaceBetween: 10,// 간격
  centeredSlides: true,

  navigation: {
    prevEl: '.swiper-button-prev',
    nextEl: '.swiper-button-next'
  },

  //기울기효과
  pagination: {
    el: '.swiper-pagination',
    clickable: true
  },
  effect: 'coverflow',
  coverflowEffect: {
    rotate: 30,
    slideShadows: false,
  }
});

// left player 
let songimg = document.querySelector('.image1');
songimg.innerHTML = `<img src="${All_song[0].img}" />`;

let songname = document.querySelector('#n1');
songname.innerHTML = `<p id="f1">${All_song[0].name}</p>
<p id="f2">${All_song[0].singer}</p>`;

let slen1 = document.querySelector('#current_duration1');
let sflen1 = document.querySelector('#total_duration1');

//left
var audio = document.getElementById('audio1');
console.log(typeof audio + " " + audio);
    // span 객체 얻기
    var current = document.getElementById('current_duration1');
    var total = document.getElementById('total_duration1');
    // progress 객체 얻기
    var progress = document.getElementById('progress');

    // Audio 객체에 timeupdate 이벤트 처리를 위한 리스너 부착
    // addEventListener() 함수 사용
    audio.addEventListener('timeupdate', function () {
        // 오디오의 총 재생시간 얻고 설정
        total.innerHTML = audio.duration;
        // 오디오의 현재 재생시간 얻고 설정
        current.innerHTML = audio.currentTime;
        // 타임 업데이트에 따른 프로그레스 진행정도 표시
        // 최대값 설정
        progress.max = audio.duration;
        // 진행 정도
        progress.value = audio.currentTime;
    });

// 재생버튼시 호출하는 함수
var play = function () {
    audio.play();
};

// 일시정지버튼시 호출하는 함수
var pause = function () {
    audio.pause();
};


const musicAudio = document.querySelector("#audio1");
musicAudio.src = "https://movie4team.s3.ap-northeast-2.amazonaws.com/Howls+Moving+Castle+OST++Theme+Song.mp3";

$(function () {
  var check = true;
  $(".p1").click(function () {
    if (check) {
      audio.play();
      musicAudio.play();
      $(this).attr("src", "images/pause.png");
      check = false;
    } else if (!check) {
      musicAudio.pause();
      $(this).attr("src", "images/playb.png");
      check = true;
    }
  });
});







const musicAudio2 = document.querySelector("#audio2");
musicAudio2.src = "https://movie4team.s3.ap-northeast-2.amazonaws.com/y2mate.com+-+%E9%9F%A9%E5%9B%BDponyo.mp3";

$(function () {
  var check = true;
  $(".p2").click(function () {
    if (check) {
      musicAudio2.play();
      $(this).attr("src", "images/pause.png");
      check = false;
    } else if (!check) {
      musicAudio2.pause();
      $(this).attr("src", "images/playb.png");
      check = true;
    }
  });
});


musicAudio2.onprogress = function () { myScript };
musicAudio2.addEventListener("progress", myScript);




const musicAudio3 = document.querySelector("#audio3");
musicAudio3.src = "https://movie4team.s3.ap-northeast-2.amazonaws.com/Always+With+Me.mp3";
$(function () {
  var check = true;
  $(".p3").click(function () {
    if (check) {
      musicAudio3.play();
      $(this).attr("src", "images/pause.png");
      check = false;
    } else if (!check) {
      musicAudio3.pause();
      $(this).attr("src", "images/playb.png");
      check = true;
    }
  });
});

const musicAudio4 = document.querySelector("#audio4");
musicAudio4.src = "https://movie4team.s3.ap-northeast-2.amazonaws.com/y2mate.com+-+Kikis+Delivery+Service++Umi+No+Mieru+Machi+Piano.mp3";
$(function () {
  var check = true;
  $(".p4").click(function () {
    if (check) {
      musicAudio4.play();
      $(this).attr("src", "images/pause.png");
      check = false;
    } else if (!check) {
      musicAudio4.pause();
      $(this).attr("src", "images/playb.png");
      check = true;
    }
  });
});

$(function () {
  var check = true;
  $(".swiper-button-next").click(function () {
    musicAudio.pause();
    document.getElementById('playimg').src("images/pause.png");
    check = false;
    document.body.style.backgroundImage = url("images/2.jpg");
  });
});

const hz = document.getElementById("audio1");

// 현재 재생위치 얻기
hz.addEventListener("timeupdate", getCurTime);
function getCurTime() {
  document.getElementsByClassName('.durations')[0].innerHTML = hz.currentTime;
}

//sflen1.innerHTML="2:33";
//sflen1.innerHTML=musicObj.duration;
/*
const progressBar = document.querySelector("#slider");
 // 뮤직 진행 바
 musicAudio.addEventListener("timeupdate", e => {
  console.log(e);

  const currentTime = e.target.currentTime;           //현재 재생 시간
  const duration = e.target.duration;                 // 오디오의 총 길이
  
  let progressWidth = (currentTime/duration) * 100;   // 전체 길이에서 현재 진행되는 시간을 백분위로 나눠줌
  ProgressBar.style.width = `${progressWidth}%`;

  // 전체시간
  musicAudio.addEventListener("loadeddata", () => {
      let audioDuration = musicAudio.duration;
      let totalMin = Math.floor(audioDuration / 60);  // 나눴을때 몫(분단위)
      let totalSec = Math.floor(audioDuration % 60);  // 나눴을때 나머지 값(초단위)

      if(totalSec < 10) totalSec = `0${totalSec}`;    // 초가 한 자릿수일 때 앞에 0을 붙임
      let str= `${totalMin}:${totalSec}`;    // 완성된 시간 표현
      sflen1.innerHTML ="0:12"
  });

  // 진행시간
  let currentMin = Math.floor(currentTime / 60);
  let currentSec = Math.floor(currentTime % 60);

  if(currentSec < 10) currentSec = `0${currentSec}`;
  slent1.innerHTML = `${currentMin}:${currentSec}`;

});



/*
musicObj.addEventListener(e){

}


    // 재생 속도 변경을 위한 슬라이더 생성
    const playbackRateSlider = document.querySelector('#playback-rate');
    playbackRateSlider.addEventListener('input', () => {
      // 사용자가 슬라이더를 조작하면 playbackRate 속성(오디오 재생 속도)가 변경
      audioElement.playbackRate = playbackRateSlider.value;
    });

    // 오디오 재생
    audioSource.connect(audioContext.destination); // 오디오 소스를 오디오 컨텍스트의 출력에 연결
    audioElement.play();
*/

//sflen1.innerHTML="4:00";











let songimg2 = document.querySelector('.image2');
songimg2.innerHTML = `<img src="${All_song[2].img}" />`;

let songname2 = document.querySelector('#n1');
songname2.innerHTML = `<p id="f1">${All_song[2].name}</p>
<p id="f2">${All_song[2].singer}</p>`;

const musicObj2 = new Audio();
musicObj2.src = "music/2.mp3";

$(function () {
  var check = true;
  $(".p2").click(function () {
    if (check) {
      musicObj2.play();
      $(this).attr("src", "images/pause.png");
      check = false;
    } else if (!check) {
      musicObj2.pause();
      $(this).attr("src", "images/playb.png");
      check = true;
    }
  });
});


$(function () {
  var check = true;
  $("#playimg").click(function () {
    if (check) {
      musicObj.play();
      $(this).attr("src", "images/pause.png");
      check = false;
    } else if (!check) {
      musicObj.pause();
      $(this).attr("src", "images/playb.png");
      check = true;
    }
  });
});


//const myAudio = new Audio();
//myAudio.src = `${All_song[2].path}`;

/**
let songaudio = document.querySelector('.songinfo');
songaudio.innerHTML = `<audio id="audio" src="${All_song[2].path}"></audio>`;

function play() {
  var aud = document.getElementById("audio");
  aud.play();
}

let songaudio = document.querySelector('.music');
songaudio.innerHTML = `<source src="${All_song[2].path}">`;


var audio = document.getElementById("audio");
$(function () {
  $('.play').on('click', function (e) {
    e = e || window.event;
    var btn = e.target;
    if (!audio.paused) {
      audio.pause();
      isPlaying = false;
    } else {
      audio.play();
      isPlaying = true;
    }
  });
});
 */



// 오디오 재생: 크롬브라우저에서 작동 안함


/*
// 버튼 취득
const btnPlay = document.getElementById("btnPlay");
const btnPause = document.getElementById("btnPause");
const btnStop = document.getElementById("btnStop");

// 재생 버튼
btnPlay.onclick = function () {
  if (myAudio.play) {
    myAudio.pause();
    isPlaying = false;
  } else if (myAudio.pause) {
    myAudio.play();
    isPlaying = true;
  }
}



const audioContainer = document.querySelector('#audioContainer'); //HTML에서 <audio> 태그 
const playBtn = document.querySelector('.btnPlay'); // play 버튼
const stopBtn = document.querySelector('.btnPause') // stop 버튼

playBtn.addEventListener('click', loadAudio);
stopBtn.addEventListener('click', stopAudio);

function loadAudio() {
  if (myAudio.play) {
    myAudio.pause();
    isPlaying = false;
  } else if (myAudio.pause) {
    myAudio.play();
    isPlaying = true;
  }

  playAudio();
}
*/
