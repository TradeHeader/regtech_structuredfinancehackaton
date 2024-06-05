package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.BuyerSeller;
import cdm.base.staticdata.party.validation.BuyerSellerTypeFormatValidator;
import cdm.base.staticdata.party.validation.BuyerSellerValidator;
import cdm.base.staticdata.party.validation.exists.BuyerSellerOnlyExistsValidator;
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
@RosettaMeta(model=BuyerSeller.class)
public class BuyerSellerMeta implements RosettaMetaData<BuyerSeller> {

	@Override
	public List<Validator<? super BuyerSeller>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BuyerSeller, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BuyerSeller> validator() {
		return new BuyerSellerValidator();
	}

	@Override
	public Validator<? super BuyerSeller> typeFormatValidator() {
		return new BuyerSellerTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BuyerSeller, Set<String>> onlyExistsValidator() {
		return new BuyerSellerOnlyExistsValidator();
	}
}
