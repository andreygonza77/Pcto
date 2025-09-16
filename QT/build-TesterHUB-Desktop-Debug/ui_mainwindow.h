/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.12.8
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralwidget;
    QLabel *label;
    QLabel *label_2;
    QLabel *label_3;
    QLabel *label_4;
    QLabel *label_5;
    QLabel *label_6;
    QLabel *label_7;
    QLabel *label_8;
    QTextBrowser *output;
    QLabel *label_9;
    QLineEdit *linkEdit;
    QLineEdit *contentEdit;
    QCheckBox *checkBox;
    QCheckBox *checkBox_2;
    QPushButton *ch6;
    QPushButton *ch5;
    QPushButton *ch4;
    QPushButton *ch3;
    QPushButton *ch2;
    QPushButton *ch1;
    QMenuBar *menubar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(800, 441);
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        label = new QLabel(centralwidget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(10, 10, 131, 17));
        label_2 = new QLabel(centralwidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setGeometry(QRect(10, 70, 301, 17));
        label_3 = new QLabel(centralwidget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setGeometry(QRect(10, 270, 31, 17));
        label_4 = new QLabel(centralwidget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setGeometry(QRect(70, 270, 31, 17));
        label_5 = new QLabel(centralwidget);
        label_5->setObjectName(QString::fromUtf8("label_5"));
        label_5->setGeometry(QRect(130, 270, 31, 17));
        label_6 = new QLabel(centralwidget);
        label_6->setObjectName(QString::fromUtf8("label_6"));
        label_6->setGeometry(QRect(190, 270, 31, 17));
        label_7 = new QLabel(centralwidget);
        label_7->setObjectName(QString::fromUtf8("label_7"));
        label_7->setGeometry(QRect(250, 270, 31, 17));
        label_8 = new QLabel(centralwidget);
        label_8->setObjectName(QString::fromUtf8("label_8"));
        label_8->setGeometry(QRect(310, 270, 31, 17));
        output = new QTextBrowser(centralwidget);
        output->setObjectName(QString::fromUtf8("output"));
        output->setGeometry(QRect(450, 30, 341, 341));
        label_9 = new QLabel(centralwidget);
        label_9->setObjectName(QString::fromUtf8("label_9"));
        label_9->setGeometry(QRect(450, 10, 131, 17));
        linkEdit = new QLineEdit(centralwidget);
        linkEdit->setObjectName(QString::fromUtf8("linkEdit"));
        linkEdit->setGeometry(QRect(10, 30, 291, 25));
        contentEdit = new QLineEdit(centralwidget);
        contentEdit->setObjectName(QString::fromUtf8("contentEdit"));
        contentEdit->setGeometry(QRect(12, 90, 291, 25));
        checkBox = new QCheckBox(centralwidget);
        checkBox->setObjectName(QString::fromUtf8("checkBox"));
        checkBox->setGeometry(QRect(20, 140, 92, 23));
        checkBox_2 = new QCheckBox(centralwidget);
        checkBox_2->setObjectName(QString::fromUtf8("checkBox_2"));
        checkBox_2->setGeometry(QRect(130, 140, 92, 23));
        ch6 = new QPushButton(centralwidget);
        ch6->setObjectName(QString::fromUtf8("ch6"));
        ch6->setGeometry(QRect(10, 300, 41, 81));
        ch6->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:1, y1:1, x2:1, y2:1, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));
        ch5 = new QPushButton(centralwidget);
        ch5->setObjectName(QString::fromUtf8("ch5"));
        ch5->setGeometry(QRect(70, 300, 41, 81));
        ch5->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:1, y1:1, x2:1, y2:1, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));
        ch4 = new QPushButton(centralwidget);
        ch4->setObjectName(QString::fromUtf8("ch4"));
        ch4->setGeometry(QRect(130, 300, 41, 81));
        ch4->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:1, y1:1, x2:1, y2:1, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));
        ch3 = new QPushButton(centralwidget);
        ch3->setObjectName(QString::fromUtf8("ch3"));
        ch3->setGeometry(QRect(190, 300, 41, 81));
        ch3->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:1, y1:1, x2:1, y2:1, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));
        ch2 = new QPushButton(centralwidget);
        ch2->setObjectName(QString::fromUtf8("ch2"));
        ch2->setGeometry(QRect(250, 300, 41, 81));
        ch2->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:1, y1:1, x2:1, y2:1, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));
        ch1 = new QPushButton(centralwidget);
        ch1->setObjectName(QString::fromUtf8("ch1"));
        ch1->setGeometry(QRect(310, 300, 41, 81));
        ch1->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:1, y1:1, x2:1, y2:1, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));
        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 22));
        MainWindow->setMenuBar(menubar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", nullptr));
        label->setText(QApplication::translate("MainWindow", "Inserire link", nullptr));
        label_2->setText(QApplication::translate("MainWindow", "Inserire contenuto da inviare", nullptr));
        label_3->setText(QApplication::translate("MainWindow", "CH6", nullptr));
        label_4->setText(QApplication::translate("MainWindow", "CH5", nullptr));
        label_5->setText(QApplication::translate("MainWindow", "CH4", nullptr));
        label_6->setText(QApplication::translate("MainWindow", "CH3", nullptr));
        label_7->setText(QApplication::translate("MainWindow", "CH2", nullptr));
        label_8->setText(QApplication::translate("MainWindow", "CH1", nullptr));
        label_9->setText(QApplication::translate("MainWindow", "Risposta:", nullptr));
        checkBox->setText(QApplication::translate("MainWindow", "Get", nullptr));
        checkBox_2->setText(QApplication::translate("MainWindow", "Post", nullptr));
        ch6->setText(QString());
        ch5->setText(QString());
        ch4->setText(QString());
        ch3->setText(QString());
        ch2->setText(QString());
        ch1->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
