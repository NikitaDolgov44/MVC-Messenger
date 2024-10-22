document.addEventListener("DOMContentLoaded", function() {
    const registrationForm = document.getElementById("registrationForm");
    const messageContainer = document.getElementById("messageContainer");

    registrationForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Остановить обычное поведение формы

        const formData = new FormData(registrationForm);
        const requestOptions = {
            method: "POST",
            body: formData
        };

        fetch(registrationForm.action, requestOptions)
            .then(response => {
                if (!response.ok) {
                    return response.json().then(data => {
                        showMessage(data.message, "error");
                    });
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    showMessage("Регистрация успешна!", "success");
                    // Можно перенаправить пользователя на страницу входа или главную
                    window.location.href = "/login"; // перенаправление
                }
            })
            .catch(error => {
                showMessage("Произошла ошибка. Пожалуйста, попробуйте позже.", "error");
            });
    });

    function showMessage(message, type) {
        messageContainer.textContent = message;
        messageContainer.className = `message ${type}`; // Добавляем класс для стилизации
        messageContainer.style.display = "block"; // Отображаем сообщение
    }
});
