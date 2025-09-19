const ch6 = document.querySelector(".ch6");
const ch5 = document.querySelector(".ch5");
const ch4 = document.querySelector(".ch4");
const ch3 = document.querySelector(".ch3");
const ch2 = document.querySelector(".ch2");
const ch1 = document.querySelector(".ch1");
const body = document.querySelector(".body");
const mandaGet = document.querySelector(".mandaGet");
const mandaPost = document.querySelector(".mandaPost");
const urlGet = "http://10.100.0.77/get_rele_status";

async function getStatus(){
    let status = [false, false, false, false, false, false];
    let r = ["0,0,0,0,0,0"];
    try{
        const response = await fetch(urlGet);
        if(response.ok){
            const responseBody = await response.text();
            const r = responseBody.split(",");
            for(let i = 0; i < r.length; i++){
                status[i] = r[i].trim() === "1";
            }
        }
        else{
            const p = document.createElement("p");
            p.innerHTML = "Errore nella risposta: " + response.text();
            body.append(p);
        }
    }
    catch(e){
        const p = document.createElement("p");
        p.innerHTML = "Errore: " + e;
        body.append(p);
    }
    return status;
}

function setColor(status){
    btns = [ch6, ch5, ch4, ch3, ch2, ch1];
    for(let i = 0; i < btns.length; i++){
        if(status[i]){
            btns[i].classList.remove("btn-dark");
            btns[i].classList.add("btn-danger");
        }
        else{
            btns[i].classList.remove("btn-danger");
            btns[i].classList.add("btn-dark");
        }
    }
}

async function updateRelays(){
    let status = await getStatus();
    setColor(status);
}

document.addEventListener("DOMContentLoaded", updateRelays);
mandaGet.addEventListener("click", updateRelays);
mandaPost.addEventListener("click", updateRelays);