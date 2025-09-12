#include <iostream>
#include <cstring>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

const int PORT = 8080;

void handle_request(int client_socket) {
    // Ricevi la richiesta dal client
    char buffer[1024];
    int bytes_received = recv(client_socket, buffer, sizeof(buffer) - 1, 0);
    if (bytes_received < 0) {
        std::cerr << "Errore nella ricezione dei dati" << std::endl;
        return;
    }

    buffer[bytes_received] = '\0';  // Termina la stringa correttamente

    // Mostra la richiesta ricevuta
    std::cout << "Ricevuto: " << buffer << std::endl;

    // Prepara la risposta HTTP
    const char *http_response =
        "HTTP/1.1 200 OK\r\n"
        "Content-Type: text/html\r\n"
        "Connection: close\r\n"
        "\r\n"
        "<html><body><h1>Benvenuto al server HTTP!</h1></body></html>";

    // Invia la risposta al client
    send(client_socket, http_response, strlen(http_response), 0);

    // Chiudi la connessione con il client
    close(client_socket);
}

int main() {
    // Crea un socket per il server
    int server_socket = socket(AF_INET, SOCK_STREAM, 0);
    if (server_socket < 0) {
        std::cerr << "Errore nella creazione del socket" << std::endl;
        return 1;
    }

    // Imposta l'opzione SO_REUSEADDR per riutilizzare la porta
    int optval = 1;
    if (setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval)) < 0) {
        std::cerr << "Errore nell'impostare SO_REUSEADDR" << std::endl;
        return 1;
    }

    // Imposta l'indirizzo del server
    sockaddr_in server_addr{};
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(PORT);

    // Associa il socket all'indirizzo
    if (bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0) {
        std::cerr << "Errore nel bind del socket sulla porta " << PORT << std::endl;
        return 1;
    }

    // Inizia ad ascoltare le connessioni in ingresso
    if (listen(server_socket, 10) < 0) {
        std::cerr << "Errore nell'ascolto delle connessioni" << std::endl;
        return 1;
    }

    std::cout << "Server in ascolto sulla porta " << PORT << "..." << std::endl;

    // Ciclo principale per accettare le connessioni
    while (true) {
        // Accetta una connessione in ingresso
        int client_socket = accept(server_socket, nullptr, nullptr);
        if (client_socket < 0) {
            std::cerr << "Errore nell'accettare la connessione" << std::endl;
            continue;
        }

        // Gestisce la richiesta del client
        handle_request(client_socket);
    }

    // Chiudi il socket del server
    close(server_socket);
    return 0;
}
