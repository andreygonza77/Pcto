#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QLineEdit>
#include <QCheckBox>
#include <curlpp/cURLpp.hpp>
#include <curlpp/Easy.hpp>
#include <curlpp/Options.hpp>
#include <curlpp/Exception.hpp>

#include <cstdlib>


QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE


class MyWindow
{

public:

    int writeDebug(curl_infotype, char *, size_t)
    {
        curlpp::raiseException(std::runtime_error("This is our exception."));
        std::cout << "We never reach this line." << std::endl;
        return 0;
    }
};

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();




private slots:
    void MyTimerSlot();

    void on_getBtn_clicked();

    void on_postBtn_clicked();

    void on_linkEdit_returnPressed();

    void on_checkBox_stateChanged(int arg1);

    void on_ch6_clicked();

private:
    Ui::MainWindow *ui;
    QLineEdit *link;
    QCheckBox *getCheckBox;
};
#endif // MAINWINDOW_H
