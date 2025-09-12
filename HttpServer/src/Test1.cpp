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
 * per eliminare un processo:
 * lsof -i ;PORT -> restituisce info tra cui PID
 * kill -9 PID -> uccide il processo
 *
*/

void handleRequest(int clientSocket) {
    char buffer[MAX];
    int bytesReceived = recv(clientSocket, buffer, sizeof(buffer) - 1, 0);
    if (bytesReceived < 0) {
        cerr << "Errore: La ricezione dei dati non è andata a buon fine" << endl;
        return;
    }
    buffer[bytesReceived] = '\0';  // Terminazione stringa

    // Stampa la richiesta per il debug
    cout << "Ricevuto: " << buffer << endl;

    // Estrai il metodo HTTP (GET, POST, ecc.)
    char method[10], path[100];
    sscanf(buffer, "%s %s", method, path); // Leggi il metodo e il percorso

    if (strcmp(method, "GET") == 0) {
        // Gestione GET (simile a quello che hai già fatto)
        char* queryParams = strchr(path, '?');
        if (queryParams) {
            queryParams++;
            cout << "Parametri GET: " << queryParams << endl;
        }

        const char *httpResponse =
            "HTTP/1.1 200 OK\r\n"
            "Content-Type: text/html\r\n"
            "Connection: close\r\n"
            "\r\n"
            "<html><body><h1>GET: Benvenuto al server HTTP!</h1></body></html>";
        send(clientSocket, httpResponse, strlen(httpResponse), 0);
    } else if (strcmp(method, "POST") == 0) {
        // Gestione POST
        // Trova il campo Content-Length per sapere quanti byte ci sono nel corpo della richiesta
        const char *contentLengthHeader = "Content-Length: ";
        char *contentLengthPos = strstr(buffer, contentLengthHeader);
        int contentLength = 0;

        if (contentLengthPos) {
            contentLength = atoi(contentLengthPos + strlen(contentLengthHeader));
        }

        if (contentLength > 0) {
            char postData[contentLength + 1];
            int bytesReceived = recv(clientSocket, postData, contentLength, 0);
            if (bytesReceived < 0) {
                cerr << "Errore nella ricezione dei dati POST" << endl;
                return;
            }
            postData[bytesReceived] = '\0'; // Assicurati che la stringa sia terminata
            cout << "Dati POST ricevuti: " << postData << endl;

            char nome[50], eta[10];
            if (sscanf(postData, "nome=%49[^&]&eta=%9s", nome, eta) == 2) {
                cout << "Nome: " << nome << ", Età: " << eta << endl;
            }
        }
        const char *httpResponse =
            "HTTP/1.1 200 OK\r\n"
            "Content-Type: text/html\r\n"
            "Connection: close\r\n"
            "\r\n"
            "POST: Dati ricevuti correttamente!";

        send(clientSocket, httpResponse, strlen(httpResponse), 0);
    } else {
        // Metodo non supportato
        const char *httpResponse =
            "HTTP/1.1 405 Method Not Allowed\r\n"
            "Content-Type: text/html\r\n"
            "Connection: close\r\n"
            "\r\n"
            "Errore: Metodo non permesso (codice 405)";
        send(clientSocket, httpResponse, strlen(httpResponse), 0);
    }
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
