function getInputData(){
    let inputValue=$("#data-input").val();
    console.log(inputValue);
}

function replaceDataOnDisplay(){
    let inputValue=$("#data-input").val();
    $("#display-data").text(inputValue);
}