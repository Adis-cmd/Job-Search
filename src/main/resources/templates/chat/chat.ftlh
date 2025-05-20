<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        body {
            background-color: #1e1e2f;
            font-family: 'Roboto', sans-serif;
            color: #f1f1f1;
            margin: 0;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            max-width: 1200px;
            margin: auto;
        }

        .chat-section {
            display: flex;
            flex-direction: row;
            background: #25263a;
            border-radius: 20px;
            padding: 2rem;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 1100px;
        }

        .chat-area {
            flex: 3;
            display: flex;
            flex-direction: column;
            margin-right: 2rem;
        }

        .chat-box {
            background: #2d2f45;
            border-radius: 14px;
            padding: 1rem;
            height: 500px;
            overflow-y: auto;
            margin-bottom: 1rem;
            box-shadow: inset 0 0 10px rgba(0,0,0,0.3);
            scroll-behavior: smooth;
        }

        .message {
            background: #373951;
            padding: 0.9rem;
            border-radius: 10px;
            margin-bottom: 0.8rem;
            word-wrap: break-word;
            transition: 0.3s;
        }

        .message:hover {
            background: #444666;
        }

        .message a {
            color: #70f3c5;
            text-decoration: underline;
        }

        .form-area {
            display: flex;
            flex-direction: column;
        }

        textarea {
            background: #2d2f45;
            border: none;
            color: #fff;
            border-radius: 10px;
            padding: 0.8rem;
            font-size: 1rem;
            margin-bottom: 0.8rem;
            resize: none;
        }

        textarea:focus {
            outline: 2px solid #70f3c5;
        }

        .button-primary {
            background: linear-gradient(135deg, #70f3c5, #4bd8a4);
            border: none;
            border-radius: 10px;
            color: #1e1e2f;
            padding: 0.9rem;
            font-weight: bold;
            font-size: 1rem;
            cursor: pointer;
            transition: 0.3s;
        }

        .button-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(0,0,0,0.2);
        }

        .profile-container {
            flex: 1;
            background: #2d2f45;
            border-radius: 20px;
            padding: 1.5rem;
            text-align: center;
            box-shadow: 0 8px 20px rgba(0,0,0,0.25);
        }

        .profile-container img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 3px solid #70f3c5;
            margin-bottom: 1rem;
            object-fit: cover;
        }

        .profile-container h4 {
            margin: 0.5rem 0;
            color: #fff;
            font-size: 1.3rem;
        }

        .profile-container p {
            margin: 0.4rem 0;
            color: #aaa;
        }
    </style>

    <div class="container">
        <div class="chat-section">
            <div class="chat-area">
                <div class="chat-box" id="chatBox"></div>
                <form id="messageForm" class="form-area">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}" />
                    </#if>
                    <textarea id="messageContent" rows="2" placeholder="Введите сообщение..."></textarea>
                    <button type="button" id="sendMessageBtn" class="button-primary">Отправить</button>
                </form>
            </div>
            <div class="profile-container">
                <#if guestUser.accountType == "EMPLOYER">
                    <h4><@spring.message 'chat.employerDetails'/></h4>
                    <img src="/users/photos/${guestUser.avatar}" alt="Avatar">
                    <p><@spring.message 'company.name'/>: ${guestUser.name}</p>
                <#else>
                    <h4>Пользователь</h4>
                    <img src="/users/photos/${guestUser.avatar}" alt="Avatar">
                    <p>Имя: ${guestUser.name}</p>
                </#if>
                <p>Email: ${guestUser.email}</p>
                <p>Телефон: ${guestUser.phoneNumber}</p>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {

            function isResumeLink(content) {
                const resumeLinkRegex = /^\/resumes\/\d+$/;
                return resumeLinkRegex.test(content);
            }

            function getResumeIdFromLink(content) {
                const resumeLinkRegex = /^\/resumes\/(\d+)$/;
                const match = content.match(resumeLinkRegex);
                return match && match[1] ? match[1] : null;
            }

            function loadMessages() {
                $.ajax({
                    url: '/api/chat/${respondedApplicantId}/messages',
                    method: 'GET',
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                    },
                    success: function (messages) {
                        $('#chatBox').empty();
                        messages.forEach(function (message) {
                            let messageContent = '<div class="message"><strong>' +
                                message.userFromId.name + ':</strong> ';

                            if (isResumeLink(message.content)) {
                                const resumeId = getResumeIdFromLink(message.content);
                                messageContent += resumeId
                                    ? '<a href="/resumes/' + resumeId + '">' + message.content + '</a>'
                                    : message.content;
                            } else {
                                messageContent += message.content;
                            }

                            messageContent += '</div>';
                            $('#chatBox').append(messageContent);
                        });

                        const chatBox = document.getElementById('chatBox');
                        chatBox.scrollTop = chatBox.scrollHeight;
                    },
                    error: function () {
                        console.error('Ошибка загрузки сообщений');
                    }
                });
            }

            loadMessages();
            setInterval(loadMessages, 5000);

            $('#sendMessageBtn').click(function () {
                const messageContent = $('#messageContent').val();
                if (!messageContent.trim()) return;

                $.ajax({
                    url: '/api/chat/${respondedApplicantId}/send',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        userFromId: ${userFromId},
                        userToId: ${userToId},
                        respondedApplicantsId: ${respondedApplicantId},
                        content: messageContent,
                        timestamp: new Date().toISOString()
                    }),
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
                    },
                    success: function () {
                        loadMessages();
                        $('#messageContent').val('');
                    },
                    error: function () {
                        console.error('Ошибка отправки сообщения');
                    }
                });
            });
        });
    </script>
</@main.layout>
