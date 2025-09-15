#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>
#include <QApplication>
#include <QLineEdit>
#include <QCheckBox>
#include <string>
#include <iostream>

using namespace std;

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    link = ui->linkEdit;
    getCheckBox = ui->checkBox;
     //connect(ui->getBtn, &QPushButton::clicked, this, &MainWindow::on_getBtn_clicked);

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
    if(arg1 == Qt::Checked){
        qDebug() << "Checkbox selezionato";
    }
    else if(arg1 == Qt::Unchecked) {
        qDebug() << "Checkbox non selezionato";
    }
}

