package cdm.product.collateral.validation.exists;

import cdm.base.staticdata.party.metafields.ReferenceWithMetaAccount;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.product.collateral.IndependentAmount;
import cdm.product.common.settlement.PaymentDetail;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class IndependentAmountOnlyExistsValidator implements ValidatorWithArg<IndependentAmount, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends IndependentAmount> ValidationResult<IndependentAmount> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payerPartyReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getPayerPartyReference()))
				.put("payerAccountReference", ExistenceChecker.isSet((ReferenceWithMetaAccount) o.getPayerAccountReference()))
				.put("receiverPartyReference", ExistenceChecker.isSet((ReferenceWithMetaParty) o.getReceiverPartyReference()))
				.put("receiverAccountReference", ExistenceChecker.isSet((ReferenceWithMetaAccount) o.getReceiverAccountReference()))
				.put("paymentDetail", ExistenceChecker.isSet((List<? extends PaymentDetail>) o.getPaymentDetail()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("IndependentAmount", ValidationType.ONLY_EXISTS, "IndependentAmount", path, "");
		}
		return failure("IndependentAmount", ValidationType.ONLY_EXISTS, "IndependentAmount", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
