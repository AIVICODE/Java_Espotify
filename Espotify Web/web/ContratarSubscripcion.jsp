<%-- 
    Document   : ContratarSubscripcion
    Created on : Oct 19, 2024, 3:42:33 PM
    Author     : ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suscripción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #121212;
            color: white;
        }
        .card {
            background-color: #27272a;
        }
        .btn-success {
            background-color: #22c55e;
            border-color: #22c55e;
        }
        .btn-success:hover {
            background-color: #16a34a;
            border-color: #16a34a;
        }
    </style>
</head>

<body>
    <jsp:include page="header.jsp" />
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6">
                <div class="card text-white">
                    <div class="card-body">
                        <h5 class="card-title text-center mb-4">Elige tu Suscripción</h5>
                        <form id="subscriptionForm">
                            <div class="mb-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="subscriptionOption" id="weekly" value="semanal">
                                    <label class="form-check-label" for="weekly">
                                        Semanal - $9.99
                                    </label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="subscriptionOption" id="monthly" value="mensual">
                                    <label class="form-check-label" for="monthly">
                                        Mensual - $29.99
                                    </label>
                                </div>
                            </div>
                            <div class="mb-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="subscriptionOption" id="yearly" value="anual">
                                    <label class="form-check-label" for="yearly">
                                        Anual - $299.99
                                    </label>
                                </div>
                            </div>
                        </form>
                        <div id="confirmationArea" class="text-center mt-4" style="display: none;">
                            <p>Monto total: $<span id="totalAmount"></span></p>
                            <button id="confirmButton" class="btn btn-success">Confirmar Suscripción</button>
                        </div>
                        <div id="successMessage" class="text-center mt-4 text-success" style="display: none;">
                            <p>¡Suscripción confirmada! Tu suscripción está ahora en estado "Pendiente".</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('subscriptionForm');
            const confirmationArea = document.getElementById('confirmationArea');
            const totalAmountSpan = document.getElementById('totalAmount');
            const confirmButton = document.getElementById('confirmButton');
            const successMessage = document.getElementById('successMessage');

            const prices = {
                semanal: 9.99,
                mensual: 29.99,
                anual: 299.99
            };

            form.addEventListener('change', function() {
                const selectedOption = form.querySelector('input[name="subscriptionOption"]:checked');
                if (selectedOption) {
                    const price = prices[selectedOption.value];
                    totalAmountSpan.textContent = price.toFixed(2);
                    confirmationArea.style.display = 'block';
                    successMessage.style.display = 'none';
                }
            });

            confirmButton.addEventListener('click', function() {
                const selectedOption = form.querySelector('input[name="subscriptionOption"]:checked');
                if (selectedOption) {
                    confirmationArea.style.display = 'none';
                    successMessage.style.display = 'block';
                    // Aquí podrías enviar los datos a tu servidor
                    console.log('Suscripción confirmada:', selectedOption.value);
                }
            });
        });
    </script>
</body>
</html>