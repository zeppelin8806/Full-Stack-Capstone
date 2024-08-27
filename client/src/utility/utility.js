export default{

handleAxiosError(error, action){
    let message;
    if(!error.response){
        message = error.message;
    }
    else if(error.response.data?.errors?.length){
        message = "Validation error:";
        for(let valError of error.response.data.errors){
            message += `n* ${valError.defaultMessage} (${valError.field}: ${valError.rejectedValue})`;
        }
    } else {
        message = error.response.message;
    }
    alert(`The action '${action}' failed: ${message}`);
    console.error(`The action '${action} failed: `);
    console.error(error);
}

}