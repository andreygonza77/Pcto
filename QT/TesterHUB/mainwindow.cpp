#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>
#include <QApplication>
#include <QLineEdit>
#include <QCheckBox>
#include <string>
#include <iostream>
#include <sstream>
#include <QTimer>
using namespace std;
const string postLink = "http://10.100.0.77/set_rele";
const string rele6On = "RELE[6][1]";
const string rele6Off = "RELE[6][0]";
const string rele5On = "RELE[5][1]";
const string rele5Off = "RELE[5][0]";
const string rele4On = "RELE[4][1]";
const string rele4Off = "RELE[4][0]";
const string rele3On = "RELE[3][1]";
const string rele3Off = "RELE[3][0]";
const string rele2On = "RELE[2][1]";
const string rele2Off = "RELE[2][0]";
const string rele1On = "RELE[1][1]";
const string rele1Off = "RELE[1][0]";
const int MAX = 6;
const int LENGTH = 10;

const string getUrl = "http://10.100.0.77/get_rele_status";
const string postUrl = "http://10.100.0.77/set_rele";

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow), info("")
{
    ui->setupUi(this);
    link = ui->linkEdit;
    getCheckBox = ui->checkBox;




    QTimer *timer = new QTimer(this);
    connect(timer, SIGNAL(timeout()), this, SLOT(MyTimerSlot()));
    timer->start(2000);
    //connect(ui->getBtn, &QPushButton::clicked, this, &MainWindow::on_getBtn_clicked);

}

void MainWindow::MyTimerSlot()
{

    MyWindow myWindow;

    // Creation of the request.
    curlpp::Easy myRequest;

    //myRequest.setOpt(curlpp::options::Port(8080));

    myRequest.setOpt(curlpp::options::Url(std::string("http://10.100.0.77/get_rele_status")));

    using namespace curlpp::Options;
    //myRequest.setOpt(Verbose(true));
    using namespace std::placeholders;
    //myRequest.setOpt(DebugFunction(std::bind(&MyWindow::writeDebug, &myWindow, _1, _2, _3)));

    std::ostringstream os;
    curlpp::options::WriteStream ws(&os);
    myRequest.setOpt(ws);
    myRequest.perform();
    info = os.str();
    //os << myRequest;
    qDebug() << QString::fromStdString(info);
}



MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_getBtn_clicked()
{
    qDebug() << "Get BTN";
}

void MainWindow::on_postBtn_clicked()
{
    qDebug() << "Post BTN";
}

void MainWindow::on_linkEdit_returnPressed()
{
    QString url = ui->linkEdit->text();
    QString content = ui->contentEdit->text();
    bool isGetChecked = ui->checkBox->isChecked();
    bool isPostChecked = ui->checkBox_2->isChecked();

    qDebug() << "URL inserito:" << url;
    qDebug() << "Contenuto inserito:" << content;
    qDebug() << "Metodo selezionato:" << (isGetChecked ? "GET" : "POST");

    QString outputText;
    if(isGetChecked) isPostChecked = false;
    else isPostChecked = true;

    outputText += "URL: " + url + "\n";
    outputText += "Contenuto: " + content + "\n";
    outputText += "Metodo: " + QString(isGetChecked ? "GET" : "POST") + "\n";



    ui->output->setText(outputText);
}

void MainWindow::on_checkBox_stateChanged(int arg1)
{
    //    if(arg1 == Qt::Checked){
    //        qDebug() << "Checkbox selezionato";
    //    }
    //    else if(arg1 == Qt::Unchecked) {
    //        qDebug() << "Checkbox non selezionato";
    //    }
}
string trim(const string& str) {
    int start = 0;
    while (start < str.size() && isspace(str[start])) start++;

    int end = str.size() - 1;
    while (end >= 0 && isspace(str[end])) end--;

    if (start > end) return ""; // stringa vuota
    return str.substr(start, end - start + 1);
}

int split(const string& str, char delimiter, string out[MAX]) {
    istringstream ss(str);
    string token;
    int i = 0;

    while (getline(ss, token, delimiter) && i < MAX) {
        out[i++] = trim(token);
    }

    return i; // ritorna quanti elementi sono stati riempiti
}

bool* getStatus(const std::string& info){
    static bool status[MAX] = {false, false, false, false, false, false};
    static string r[MAX] = {"0,0,0,0,0,0"};
    int count = split(info, ',', r);
    for (int i = 0; i < MAX && i < count; i++) {
        status[i] = (r[i] == "1");
    }

    return status;
}

void MainWindow::sendRelayCommand(int statusIndex, string dataOn, string dataOff) { // Post
    string content = info;
    bool* x = getStatus(content);
    const string dataToSend = x[statusIndex] ? dataOff : dataOn;

    try {
        curlpp::Easy request;

        request.setOpt(new curlpp::options::Url(postUrl));
        request.setOpt(new curlpp::options::Verbose(true));

        std::list<std::string> header;
        header.push_back("Content-Type: text/html");
        request.setOpt(new curlpp::options::HttpHeader(header));

        request.setOpt(new curlpp::options::PostFields(dataToSend));
        request.setOpt(new curlpp::options::PostFieldSize(LENGTH));

        request.perform();
    }
    catch (curlpp::LogicError &e) {
        std::cout << e.what() << std::endl;
    }
    catch (curlpp::RuntimeError &e) {
        std::cout << e.what() << std::endl;
    }
}

void MainWindow::on_ch6_clicked()
{
     sendRelayCommand(5, rele6On, rele6Off);
}

void MainWindow::on_ch5_clicked()
{
    sendRelayCommand(4, rele5On, rele5Off);
}

void MainWindow::on_ch4_clicked()
{
    sendRelayCommand(3, rele4On, rele4Off);
}

void MainWindow::on_ch3_clicked()
{
    sendRelayCommand(2, rele3On, rele3Off);
}

void MainWindow::on_ch2_clicked()
{
    sendRelayCommand(1, rele2On, rele2Off);
}

void MainWindow::on_ch1_clicked()
{
    sendRelayCommand(0, rele1On, rele1Off);
}
