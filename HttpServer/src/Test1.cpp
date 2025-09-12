#include <iostream>
#include <cstring>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

const int PORT = 8080;
const int MAX = 1024;
using namespace std;

/*
 * per eliminare i processi:
 * lsof -i :8080 -> restiusce info tra cui PID
 * kill -9 (PID) -> uccide il processo
*/



void handleRequest(int clientSocket) {
    char buffer[MAX];
    int bytesReceived = recv(clientSocket, buffer, sizeof(buffer) - 1, 0);
    if (bytesReceived < 0) {
        cerr << "Errore: La ricezione dei dati non è andata a buon fine" << endl;
        return;
    }
    buffer[bytesReceived] = '\0';
    cout << "Ricevuto: " << buffer << endl;

    const char *httpResponse =
        "HTTP/1.1 200 OK\r\n"
        "Content-Type: text/html\r\n"
        "Connection: close\r\n"
        "\r\n"
        "<html><body><h1>Benvenuto al server HTTP!</h1></body></html>";

    send(clientSocket, httpResponse, strlen(httpResponse), 0);
    close(clientSocket);
}

int main() {
    int serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket < 0) {
        cerr << "Errore: Impossibile creare il socket" << endl;
        return 1;
    }

    int optval = 1;
    if (setsockopt(serverSocket, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof(optval)) < 0) {
        cerr << "Errore: Impossibile impostare SO_REUSEADDR" << endl;
        return 1;
    }

    sockaddr_in serverAddr{};
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(PORT);

    if (bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) < 0) {
        cerr << "Errore: bind non corretto del socket sulla porta " << PORT << endl;
        return 1;
    }

    if (listen(serverSocket, 10) < 0) {
        cerr << "Errore: ascolto non corretto delle connessioni" << endl;
        return 1;
    }

    cout << "Server in ascolto sulla porta " << PORT << "..." << endl;

    while (true) {
        int clientSocket = accept(serverSocket, nullptr, nullptr);
        if (clientSocket < 0) {
            cerr << "Errore: Impossibile accettare la connessione" << endl;
            continue;
        }
        handleRequest(clientSocket);
    }

    close(serverSocket);
    return 0;
}
