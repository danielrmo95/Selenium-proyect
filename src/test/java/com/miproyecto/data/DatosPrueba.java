package com.miproyecto.data;

public class DatosPrueba {
    public static final String INSTRUCCIONES = " Vamos a recrear el uso de un chatbot para inscribir a un estudiante en un diplomado, cubriendo los siguientes casos: Reconocimiento de entidades: El chatbot saluda y consulta cómo puede ayudar.   Pausa prolongada: Valida los datos y pregunta si desea continuar con el pago y muestra las formas de pago   Integración con pagos: Recibe la forma de pago que va a utilizar el usuario.   Corrección de curso: El chatbot actualiza la información y consulta si desea pagar con tarjeta.   Error en pago (tarjeta rechazada): El chatbot consulta si desea actualizar los datos.   Transacción exitosa. El chatbot confirma la transacción y termina con confirmación Compartiré cada caso de prueba progresivamente. Por favor, salúdame y pregúntame en qué puedes ayudarte. Empecemos con el primer caso.";
    public static final String CASO1 = "Quiero inscribirme al diplomado en Transformación Digital. Me llamo Laura Pérez y mi correo es laura@example.com";
    public static final String CASO2 = "Sí, quiero continuar con el pago";
    public static final String CASO3 = "Quiero pagar el diplomado con tarjeta de crédito";
    public static final String CASO4 = "Perdón, mejor quiero el diplomado en Ciencia de Datos";
    public static final String CASO5 = "si, los datos de la tarjeta serían Número de tarjeta: 4000 0000 0000 0002, Fecha de vencimiento: 12/26 y CVV: 123";
    public static final String CASO6 = "si, los datos correctos son Número de tarjeta: 4000 0000 0000 0002, Fecha de vencimiento: 12/26 y CVV: 234";
}
