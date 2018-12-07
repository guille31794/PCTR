% Exercise 4

% to simplify, I going to use squared images
% time measured in s
res = 4000;
s = [5.604 5.604 5.604 5.604 5.604];
% I'll use only 1000 threads fixed in fine graine 
threadsf = [100 500 750 1000 2000];
timef = [10.16 10.08 10.13 9.88 7.108];
threadsg = 4;
timeg = 8.4;
sf = s./timef;
sg = s/timeg;
plot(threadsf, sf, 'b');
%hold on;
%plot(threadsg, sg, 'r');
%hold off;