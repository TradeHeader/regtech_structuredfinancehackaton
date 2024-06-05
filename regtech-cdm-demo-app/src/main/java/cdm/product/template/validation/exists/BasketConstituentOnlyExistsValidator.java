package cdm.product.template.validation.exists;

import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.staticdata.asset.common.AssetPool;
import cdm.base.staticdata.asset.common.Index;
import cdm.base.staticdata.asset.common.Loan;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.metafields.ReferenceWithMetaCommodity;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.product.asset.ForeignExchange;
import cdm.product.template.Basket;
import cdm.product.template.BasketConstituent;
import cdm.product.template.ContractualProduct;
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

public class BasketConstituentOnlyExistsValidator implements ValidatorWithArg<BasketConstituent, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends BasketConstituent> ValidationResult<BasketConstituent> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("contractualProduct", ExistenceChecker.isSet((ContractualProduct) o.getContractualProduct()))
				.put("index", ExistenceChecker.isSet((Index) o.getIndex()))
				.put("loan", ExistenceChecker.isSet((Loan) o.getLoan()))
				.put("assetPool", ExistenceChecker.isSet((AssetPool) o.getAssetPool()))
				.put("foreignExchange", ExistenceChecker.isSet((ForeignExchange) o.getForeignExchange()))
				.put("commodity", ExistenceChecker.isSet((ReferenceWithMetaCommodity) o.getCommodity()))
				.put("security", ExistenceChecker.isSet((Security) o.getSecurity()))
				.put("basket", ExistenceChecker.isSet((Basket) o.getBasket()))
				.put("quantity", ExistenceChecker.isSet((List<? extends ReferenceWithMetaNonNegativeQuantitySchedule>) o.getQuantity()))
				.put("initialValuationPrice", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getInitialValuationPrice()))
				.put("interimValuationPrice", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getInterimValuationPrice()))
				.put("finalValuationPrice", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPriceSchedule>) o.getFinalValuationPrice()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("BasketConstituent", ValidationType.ONLY_EXISTS, "BasketConstituent", path, "");
		}
		return failure("BasketConstituent", ValidationType.ONLY_EXISTS, "BasketConstituent", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
