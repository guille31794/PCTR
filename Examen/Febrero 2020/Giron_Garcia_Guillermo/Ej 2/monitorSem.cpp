#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>

using namespace std;

class Semaphore
{
    public:
        Semaphore(unsigned);
        void waitS();
        void signalS();
    
    private:
        unsigned S, waiting;
        mutex m;
        condition_variable cv;
};

Semaphore::Semaphore(unsigned n): S{n}, waiting{0}, m{}
{}

void Semaphore::waitS()
{
    std::unique_lock<std::mutex> L(m);
    if(!S)
    {
        cv.wait(L);
        ++waiting;
    }
    else
        --S;
    L.unlock();
}

void Semaphore::signalS()
{
    std::unique_lock<std::mutex> L(m);
    if(waiting)
    {
        cv.notify_one();
        --waiting;
    }
    else
        ++S;

    L.unlock();
}

int n{0};
Semaphore::Semaphore s{1};

void incr()
{
    s.waitS();
    ++n;
    s.signalS();
}

int main(int argc, char const *argv[])
{
    unsigned inc{20000};
    Thread t1{[inc]()
    {
        for(unsigned i = 0; i < (inc/2); ++i)
            incr();
    }},
    t2{[inc]()
    {
        for(unsigned i = 0; i < (inc/2); ++i)
            incr();
    }};

    t2.join();
    t1.join();

    cout << "Value of n is " << n << " it should be " << inc
    << endl;

    return 0;
}
