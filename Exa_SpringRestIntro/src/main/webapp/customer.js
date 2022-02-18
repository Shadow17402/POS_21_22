var _url = "./customer"

const getCustomer = (customer_id) => {
    alert(customer_id)
    fetch(_url + "/" + customer_id).then(response => {
        console.log(response.status)
        showInfo("GET", response.status);
        return response.json()
    })
        .then(data => {
            for (let key in data) {
                let tf = document.getElementById(key);
                if (tf != null) {
                    tf.value = data[key];
                }
            }
        })
        .catch(err => {
            console.error(err)
        })
}

const showInfo = (status, method) =>{
    document.getElementById("info").innerHTML="HTTP-Method: "+method+"<br/>HTTP-Status: "+ status;
}

const postCustomer = () => {
    let customer = {
        "customer_id" : customerId.value,
        "firstname" : firstname.value,
        "lastname" : lastname.value,
        "gender" : gender.value,
        "active" : active.value,
        "email" : email.value,
        "since" : since.value,
    }
    fetch(_url, {
        method: "post",
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(customer)
    })
        .then(response => {
            showInfo("POST", response.status);
            _activeCustomer = response.headers.get("Location") //<-- url vom neuen customer
        })
        .catch(err => {
            console.error(err)
        })
}