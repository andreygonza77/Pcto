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

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
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

    myRequest.perform();
    std::ostringstream os;
    curlpp::options::WriteStream ws(&os);
    myRequest.setOpt(ws);
    myRequest.perform();
    string info = os.str();
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


void MainWindow::on_ch6_clicked()
{


}
