#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QDebug>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
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
    qDebug() << "Link: " + url;
}

void MainWindow::on_checkBox_stateChanged(int arg1)
{
    if(arg1 == Qt::Checked){
        qDebug() << "Checkbox selezionato";
    }
    else if(arg1 == Qt::Unchecked) {
        qDebug() << "Checkbox non selezionato";
    }
/*
    if(ui->checkBox->isChecked()){
        qDebug() << "Checkbox selezionato";
    }
    else {
        qDebug() << "Checkbox non selezionato";
    }
*/

}
