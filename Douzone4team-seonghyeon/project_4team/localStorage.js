const form = document.querySelector(".js-form"),
  input = form.querySelector("input"),
  greeting = document.querySelector(".js-greeting"),
  tag=document.querySelector(".re"),
  button = document.querySelector("button");

//저장할 데이터의 key값
const LOCAL_DATA = "nickName";

//input태그에 이름 입력 후 input태그를 감추고,
//텍스트가 나타나게 해주는 함수
function hiddenAndGreeting(name) {
  greeting.style.display = "block";
  form.style.display = "none";
  button.style.display = "block";
  button.addEventListener("click", onClick);
  greeting.innerText = `Hi ${name} !`;
  tag.innerHTML="Local Storage";
}

function onAddClick() {
  greeting.style.display = "block";
  form.style.display = "none";
  button.style.display = "block";
  localStorage.setItem("LOCAL_DATA", input.value); 
  console.log(localStorage.getItem("LOCAL_DATA"));/*chcek*/
  button.addEventListener("click", onClick);
  greeting.innerText = `Hi ${input.value} !`;
  tag.innerHTML="Local Storage"; 
  input.value = "";
}

//버튼 클릭 시, 데이터 삭제 이벤트 발생
function onClick() {
  localStorage.removeItem(LOCAL_DATA);
  tag.innerHTML='Remove !';
  console.log("remove complete");
  loaded();
}

//input태그 입력 시, lacalStorage의 value값으로 저장
function onSubmit(e) {
  e.preventDefault();
  localStorage.setItem(LOCAL_DATA, input.value);
  hiddenAndGreeting(input.value); 
  input.value = "";
}

//input태그 보이게 설정, 인사 텍스트와 버튼은 숨김.
//form에 submit 이벤트 설정
function askForNickName() {
  form.style.display = "block";
  greeting.style.display = "none";
  button.style.display = "none";
  form.addEventListener("submit", onSubmit); //form에 submit이벤트 추가
}

// 브라우저의 localStorage에 데이터가 있을때와 없을때
//구분하여 실행 시켜줄 함수.
function loaded() {
  const data = localStorage.getItem(LOCAL_DATA);
  if (data === null) { // 데이터가 없을 시
    askForNickName(); 
  } else {//데이터가 이미 있을 시
    hiddenAndGreeting(data); 
  }
}

//처음 실행할 init함수
function init() {
  loaded();
}
init();