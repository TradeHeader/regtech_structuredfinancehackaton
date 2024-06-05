package cdm.observable.asset.meta;

import cdm.observable.asset.PremiumExpression;
import cdm.observable.asset.validation.PremiumExpressionTypeFormatValidator;
import cdm.observable.asset.validation.PremiumExpressionValidator;
import cdm.observable.asset.validation.exists.PremiumExpressionOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=PremiumExpression.class)
public class PremiumExpressionMeta implements RosettaMetaData<PremiumExpression> {

	@Override
	public List<Validator<? super PremiumExpression>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PremiumExpression, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PremiumExpression> validator() {
		return new PremiumExpressionValidator();
	}

	@Override
	public Validator<? super PremiumExpression> typeFormatValidator() {
		return new PremiumExpressionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PremiumExpression, Set<String>> onlyExistsValidator() {
		return new PremiumExpressionOnlyExistsValidator();
	}
}
