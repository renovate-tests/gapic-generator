/* Copyright 2016 Google Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gapi.vgen.go;

import com.google.api.Service;
import com.google.common.collect.Multimap;
import com.google.protobuf.Api;
import com.google.protobuf.Method;

import io.gapi.vgen.ApiaryConfig;
import io.gapi.vgen.DiscoveryLanguageProvider;
import io.gapi.vgen.GeneratedResult;
import io.gapi.vgen.SnippetDescriptor;

import java.io.IOException;

/**
 * A DiscoveryLanguageProvider for generating Go fragments.
 */
public class GoDiscoveryLanguageProvider implements DiscoveryLanguageProvider {
  private final GoDiscoveryContext context;
  private final GoLanguageProvider provider;

  public GoDiscoveryLanguageProvider(Service service, ApiaryConfig apiaryConfig) {
    this.context = new GoDiscoveryContext(service, apiaryConfig);
    this.provider = new GoLanguageProvider();
  }

  @Override
  public GeneratedResult generateFragments(Method method, SnippetDescriptor snippetDescriptor) {
    return provider.generate(method, snippetDescriptor, context);
  }

  @Override
  public void output(String outputPath, Multimap<Method, GeneratedResult> methods)
      throws IOException {
    provider.output(outputPath, methods);
  }

  @Override
  public Service getService() {
    return context.getService();
  }
}