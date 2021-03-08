/*
 * Copyright 2013-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.contract.spec.internal;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class RegexPropertyTests {

	@Test
	void should_generate_utf8_compliant_text_from_regex() {
		RegexProperty regexProperty = new RegexProperty(
				Pattern.compile("......................"));

		String object = (String) regexProperty.generate();

		String utf8EncodedString = new String(object.getBytes(StandardCharsets.UTF_8),
				StandardCharsets.UTF_8);
		BDDAssertions.then(object).isEqualTo(utf8EncodedString);
	}

	@Test
	void should_generate_custom_charset_compliant_text_from_regex() {
		RegexProperty regexProperty = new RegexProperty(
				Pattern.compile("......................"));

		String object = (String) regexProperty.asString(StandardCharsets.US_ASCII)
				.generate();

		String utf8EncodedString = new String(object.getBytes(StandardCharsets.US_ASCII),
				StandardCharsets.US_ASCII);
		BDDAssertions.then(object).isEqualTo(utf8EncodedString);
	}

}
