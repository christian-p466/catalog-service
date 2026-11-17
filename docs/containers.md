# First go:
BUILD SUCCESSFUL in 770ms
4 actionable tasks: 4 up-to-date
Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.8.2/userguide/configuration_cache_enabling.html
[+] Building 16.8s (15/15) FINISHED                                                                                                                                               docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                                                              0.0s
=> => transferring dockerfile: 766B                                                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/eclipse-temurin:21-jre                                                                                                                         1.4s
=> [internal] load .dockerignore                                                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                                                   0.0s
=> [internal] load build context                                                                                                                                                                 0.7s
=> => transferring context: 28.17MB                                                                                                                                                              0.6s
=> [builder 1/4] FROM docker.io/library/eclipse-temurin:21-jre@sha256:ca73b4d3961703d6d7830a4361f49ef0478f8f28b39defc07bb72f4cc7f834f6                                                           7.8s
=> => resolve docker.io/library/eclipse-temurin:21-jre@sha256:ca73b4d3961703d6d7830a4361f49ef0478f8f28b39defc07bb72f4cc7f834f6                                                                   0.0s
=> => sha256:731e0020a2fd6fdc710baf40611fe7144d87c8aa3c1b2eebc6d2e56f1bdcfbe5 2.46kB / 2.46kB                                                                                                    0.1s
=> => sha256:43ab484bff077aab5377166f418354e991752806cd2aca44c3c0fc7bea33f4b3 160B / 160B                                                                                                        0.2s
=> => sha256:626050637c596950412d8be37c4cd941c7f338585b723861114057857664cbca 52.39MB / 52.39MB                                                                                                  1.8s
=> => sha256:620882d435ac9cf6e52f25f90d718fcd5f16cf646910f84dc90281320baddc38 19.95MB / 19.95MB                                                                                                  2.4s
=> => sha256:cedc468d06b2395acfbe15d469c1c490e32250dfff923a25b48590254da93297 386B / 386B                                                                                                        0.2s
=> => sha256:7f2c2ca81066ea08302554fea387f3df4b714e7d98ea9c9d1994683b4668b905 41.15MB / 41.15MB                                                                                                  3.7s
=> => extracting sha256:7f2c2ca81066ea08302554fea387f3df4b714e7d98ea9c9d1994683b4668b905                                                                                                         1.8s
=> => extracting sha256:cedc468d06b2395acfbe15d469c1c490e32250dfff923a25b48590254da93297                                                                                                         0.0s
=> => extracting sha256:620882d435ac9cf6e52f25f90d718fcd5f16cf646910f84dc90281320baddc38                                                                                                         0.7s
=> => extracting sha256:626050637c596950412d8be37c4cd941c7f338585b723861114057857664cbca                                                                                                         1.3s
=> => extracting sha256:43ab484bff077aab5377166f418354e991752806cd2aca44c3c0fc7bea33f4b3                                                                                                         0.0s
=> => extracting sha256:731e0020a2fd6fdc710baf40611fe7144d87c8aa3c1b2eebc6d2e56f1bdcfbe5                                                                                                         0.0s
=> [builder 2/4] WORKDIR /builder                                                                                                                                                                0.6s
=> [stage-1 2/7] RUN useradd spring                                                                                                                                                              1.2s
=> [builder 3/4] COPY build/libs/catalog-service-0.0.1-SNAPSHOT.jar application.jar                                                                                                              0.4s
=> [builder 4/4] RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted                                                                                          1.1s
=> [stage-1 3/7] WORKDIR /application                                                                                                                                                            0.0s
=> [stage-1 4/7] COPY --from=builder /builder/extracted/dependencies/ ./                                                                                                                         0.1s
=> [stage-1 5/7] COPY --from=builder /builder/extracted/spring-boot-loader/ ./                                                                                                                   0.0s
=> [stage-1 6/7] COPY --from=builder /builder/extracted/snapshot-dependencies/ ./                                                                                                                0.0s
=> [stage-1 7/7] COPY --from=builder /builder/extracted/application/ ./                                                                                                                          0.0s
=> exporting to image                                                                                                                                                                            5.2s
=> => exporting layers                                                                                                                                                                           0.6s
=> => exporting manifest sha256:a4421fd262ee7e61ad1d397b5a4eed87e151975308bbe6c6b71ec8bcfd0e753f                                                                                                 0.0s
=> => exporting config sha256:f5378c62697b5140e6f07545ca23fb38a8b342b78738e3a4f0e98b1f239ac4b5                                                                                                   0.0s
=> => exporting attestation manifest sha256:a1b4f7b57e4a9611ac6d6fb9f4fa20bd7f71ff28a62d517b0df659103565b1ce                                                                                     0.0s
=> => exporting manifest list sha256:501cb43e3956ae3e6edd508df8ea39824eef7d6561d27afb336c55fdb0a11acd                                                                                            0.0s
=> => naming to docker.io/library/catalog-service:dockerfile                                                                                                                                     0.0s
=> => unpacking to docker.io/library/catalog-service:dockerfile 

# Second
BUILD SUCCESSFUL in 1s
4 actionable tasks: 3 executed, 1 up-to-date
Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.8.2/userguide/configuration_cache_enabling.html
[+] Building 4.0s (15/15) FINISHED                                                                                                                                                docker:desktop-linux
=> [internal] load build definition from Dockerfile                                                                                                                                              0.0s
=> => transferring dockerfile: 766B                                                                                                                                                              0.0s
=> [internal] load metadata for docker.io/library/eclipse-temurin:21-jre                                                                                                                         0.4s
=> [internal] load .dockerignore                                                                                                                                                                 0.0s
=> => transferring context: 2B                                                                                                                                                                   0.0s
=> [internal] load build context                                                                                                                                                                 0.6s
=> => transferring context: 28.17MB                                                                                                                                                              0.6s
=> [builder 1/4] FROM docker.io/library/eclipse-temurin:21-jre@sha256:ca73b4d3961703d6d7830a4361f49ef0478f8f28b39defc07bb72f4cc7f834f6                                                           0.0s
=> => resolve docker.io/library/eclipse-temurin:21-jre@sha256:ca73b4d3961703d6d7830a4361f49ef0478f8f28b39defc07bb72f4cc7f834f6                                                                   0.0s
=> CACHED [builder 2/4] WORKDIR /builder                                                                                                                                                         0.0s
=> [builder 3/4] COPY build/libs/catalog-service-0.0.1-SNAPSHOT.jar application.jar                                                                                                              0.4s
=> [builder 4/4] RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted                                                                                          2.4s
=> CACHED [stage-1 2/7] RUN useradd spring                                                                                                                                                       0.0s
=> CACHED [stage-1 3/7] WORKDIR /application                                                                                                                                                     0.0s
=> CACHED [stage-1 4/7] COPY --from=builder /builder/extracted/dependencies/ ./                                                                                                                  0.0s
=> CACHED [stage-1 5/7] COPY --from=builder /builder/extracted/spring-boot-loader/ ./                                                                                                            0.0s
=> CACHED [stage-1 6/7] COPY --from=builder /builder/extracted/snapshot-dependencies/ ./                                                                                                         0.0s
=> [stage-1 7/7] COPY --from=builder /builder/extracted/application/ ./                                                                                                                          0.0s
=> exporting to image                                                                                                                                                                            0.1s
=> => exporting layers                                                                                                                                                                           0.0s
=> => exporting manifest sha256:eeab6fff442636f709bd26bdd2bcdc0e046b7cf4caa5fac5ce9bc9fbb0dc5b1b                                                                                                 0.0s
=> => exporting config sha256:c1694393447e6152bd366ffdaef3d8602c2c1b9bd560fcac3a4a33287e82798a                                                                                                   0.0s
=> => exporting attestation manifest sha256:a3d7c7c0eb95b7825159da3ba403d657599b58a7c4e7070257bbe966b12aadc2                                                                                     0.0s
=> => exporting manifest list sha256:2fa0147d5a0a2a8d50303b95599f761a8cdaae46d147f2f3bc8a8dc0801af202                                                                                            0.0s
=> => naming to docker.io/library/catalog-service:dockerfile                                                                                                                                     0.0s
=> => unpacking to docker.io/library/catalog-service:dockerfile

# Checkpoint 1
- Containers have a writable layer where any changes made during a run is kept. If theres no database mounted then the database files live in that layer. When docker goes down, the files are removed and the books disappear.
