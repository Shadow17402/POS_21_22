const createElement = (tag, text, attributes = {}) => {
    const element = document.createElement(tag);
    element.innerText = text;
    for (const attribute in attributes) {
        element.setAttribute(attribute, attributes[attribute]);
    }
    return element;
}

window.onload = async () => {
    const response = await fetch("http://localhost:8080/classname", {method: 'GET'});
    const classnames = await response.json();
    let selectBox = document.getElementsByClassName("classSelect")[0];
    for (let className of classnames) {
        selectBox.appendChild(createElement('option', className.classname, {value: className.classname}));
    }
    selectBox.addEventListener('change', (e) => {
        getStudents(0, e.target.value, true);
    });
}



const getStudents = async (page, classname, newClass) => {
    const response = await fetch(`http://localhost:8080/student/getStudents?page=${page}&classname=${classname}`);
    const students = await response.json();

    if (newClass) {
        let classSelector = document.getElementById("pageSelect");
        classSelector.innerHTML = "";
        classSelector.innerText = "Page: ";
        let selectBoxPages = document.createElement('select');
        for(let i = 0; i < students["totalPages"]; i++){
            selectBoxPages.appendChild(createElement('option', i+'', {value: i}));
        }
        selectBoxPages.addEventListener('change', (event) => {
            getStudents(event.target.value, classname, false);
        });
        classSelector.appendChild(selectBoxPages);
    }

    let table = document.getElementById("studentTable");
    table.innerHTML = "";
    table.appendChild(createElement('th', 'ID'));
    table.appendChild(createElement('th', 'Firstname'));
    table.appendChild(createElement('th', 'Lastname'));
    for (student of students["content"]) {
        let tr = document.createElement('tr');
        tr.appendChild(createElement('td', student.studentId));
        tr.appendChild(createElement('td', student.firstname));
        tr.appendChild(createElement('td', student.lastname));
        tr.appendChild(createElement('button', 'Show Exams'));
        table.appendChild(tr);
    }
}