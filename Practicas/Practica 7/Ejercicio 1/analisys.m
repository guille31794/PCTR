% analisys
petitions = [10 30 50 70 100 500];
timePool = [28.7 37.17 47.24 57.39 77.447 273.598];
timeNoPool = [26.99 37.37 46.54 56.86 71.55 276.179];
plot(petitions, timePool, 'b');
hold on;
plot(petitions, timeNoPool, 'r');
hold off;