FROM alpine:3.14

RUN adduser toto --uid 3022 --disabled-password

WORKDIR /usr/src/app

COPY --chown=toto:toto . /usr/src/app

RUN chown -R toto:toto /usr/src/app/

USER toto

RUN echo "cd" >> /home/toto/.bashrc

CMD ["sh"]