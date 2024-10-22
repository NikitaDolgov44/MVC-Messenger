document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");
    const messageContainer = document.getElementById("messageContainer");

    loginForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Остановить обычное поведение формы

        const formData = new FormData(loginForm);
        const requestOptions = {
            method: "POST",
            body: formData
        };

        fetch(loginForm.action, requestOptions)
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
                    showMessage("Вход успешен!", "success");
                    // Можно перенаправить пользователя на главную страницу
                    window.location.href = "/"; // перенаправление
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

