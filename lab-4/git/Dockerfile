FROM alpine:latest
RUN apk update
RUN apk add openssh git
RUN adduser -g "Git User" -D git
RUN echo git:git | chpasswd
RUN mkdir /home/git/.ssh
ADD authorized_keys /home/git/.ssh
RUN mkdir /home/git/project.git && \
    cd /home/git/project.git && \
    echo "Initial readme" > readme.txt && \
    git init --bare && \
    git config user.email "git@gitrepo.org" && \
    git config user.name "Git"
RUN chown -R git.git /home/git && \
    chmod 700 /home/git/.ssh
RUN ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key -q -N ""
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]

