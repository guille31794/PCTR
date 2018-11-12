% Exercise 1
% time measured in seconds
% prime numbers
range = [1000000 5000000 9999999 20000000 35000000];
secTime = [0 8 21 58 125];
paralellTime = [0 3 10 26 61];

plot(range, secTime, 'g'); hold on;
plot(range, paralellTime, 'r'); 
legend('Prime Sec', 'Prime Par'); hold off;

% network
Cb = [0.4 0.5 0.6 0.7 0.8 0.9];
PoolSize = [6 8 10 13 20 39];
T = [3.713 3.428 3.802 2.421 2.541 3.349];
plot(Cb,T); legend('Time x Cb');

% network sec
Tsec = [27.558];

Tsec-T(4);

% Exercise 2
dim = [400 4000 12000 16000 20000];
ts = [8.673533E-4 0.0248670578 0.2209501624 0.383845047 0.6136149954];
tp = [0.013569154 0.13256759 1.578016108 1.984676092 3.435492864];
sp = ts./tp;
disp(sp);
plot(dim, sp); %hold on; plot(dim, tp); hold off;