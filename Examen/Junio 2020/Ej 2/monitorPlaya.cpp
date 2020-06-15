#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <chrono>

using namespace std;

class monitorPlaya
{
    unsigned aforo_current, aforo_max;
    mutex m;
    condition_variable cv;

    public:
        monitorPlaya() : aforo_current{0}, aforo_max{100} {}
        
        void entrar_playa() {
            unique_lock<mutex> lck{m};
            cout << "Persona " << this_thread::get_id << 
            " llega a la playa \n";
            while(aforo_current >= aforo_max)
            {
                cout << "Persona " << this_thread::get_id << 
                " esperando entrar a la playa.\n";
                cv.wait(lck);
            }

            aforo_current++;

            cout << "Persona " << this_thread::get_id << " entra a la playa.\n"
                 << "Aforo actual: " << aforo_current << '\n';
        }

        void salir_playa() {
            unique_lock<mutex> lck{m};

            aforo_current--;

            cout << "Persona " << this_thread::get_id <<
            " abandona la playa.\n" << "Aforo actual: "
            << aforo_current << '\n';
            
            cv.notify_all();
        }
};

void dia_de_playa(monitorPlaya& m)
{
    m.entrar_playa();
    chrono::milliseconds siesta{1000};
    this_thread::sleep_for(siesta);
    m.salir_playa();
}

int main(int argc, char const *argv[])
{
    monitorPlaya m;

    thread domingueros[200];

    for(unsigned i = 0; i < 200; ++i)
        domingueros[i] = thread{dia_de_playa, ref(m)};

    for (unsigned i = 0; i < 200; ++i)
        domingueros[i].join();

    return 0;
}
