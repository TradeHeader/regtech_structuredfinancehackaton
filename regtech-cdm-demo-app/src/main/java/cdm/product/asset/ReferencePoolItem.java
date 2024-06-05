package cdm.product.asset;

import cdm.product.asset.ProtectionTerms;
import cdm.product.asset.ReferencePair;
import cdm.product.asset.ReferencePoolItem;
import cdm.product.asset.ReferencePoolItem.ReferencePoolItemBuilder;
import cdm.product.asset.ReferencePoolItem.ReferencePoolItemBuilderImpl;
import cdm.product.asset.ReferencePoolItem.ReferencePoolItemImpl;
import cdm.product.asset.meta.ReferencePoolItemMeta;
import cdm.product.asset.metafields.ReferenceWithMetaProtectionTerms;
import cdm.product.asset.metafields.ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder;
import cdm.product.common.settlement.CashSettlementTerms;
import cdm.product.common.settlement.PhysicalSettlementTerms;
import cdm.product.common.settlement.metafields.ReferenceWithMetaCashSettlementTerms;
import cdm.product.common.settlement.metafields.ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder;
import cdm.product.common.settlement.metafields.ReferenceWithMetaPhysicalSettlementTerms;
import cdm.product.common.settlement.metafields.ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder;
import cdm.product.template.ConstituentWeight;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * This type contains all the constituent weight and reference information.
 * @version ${project.version}
 */
@RosettaDataType(value="ReferencePoolItem", builder=ReferencePoolItem.ReferencePoolItemBuilderImpl.class, version="${project.version}")
public interface ReferencePoolItem extends RosettaModelObject {

	ReferencePoolItemMeta metaData = new ReferencePoolItemMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Describes the weight of each of the constituents within the basket. If not provided, it is assumed to be equal weighted.
	 */
	ConstituentWeight getConstituentWeight();
	ReferencePair getReferencePair();
	/**
	 * Reference to the documentation terms applicable to this item.
	 */
	ReferenceWithMetaProtectionTerms getProtectionTermsReference();
	/**
	 * Reference to the cash settlement terms applicable to this item.
	 */
	ReferenceWithMetaCashSettlementTerms getCashSettlementTermsReference();
	/**
	 * Reference to the physical settlement terms applicable to this item.
	 */
	ReferenceWithMetaPhysicalSettlementTerms getPhysicalSettlementTermsReference();

	/*********************** Build Methods  ***********************/
	ReferencePoolItem build();
	
	ReferencePoolItem.ReferencePoolItemBuilder toBuilder();
	
	static ReferencePoolItem.ReferencePoolItemBuilder builder() {
		return new ReferencePoolItem.ReferencePoolItemBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferencePoolItem> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferencePoolItem> getType() {
		return ReferencePoolItem.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("constituentWeight"), processor, ConstituentWeight.class, getConstituentWeight());
		processRosetta(path.newSubPath("referencePair"), processor, ReferencePair.class, getReferencePair());
		processRosetta(path.newSubPath("protectionTermsReference"), processor, ReferenceWithMetaProtectionTerms.class, getProtectionTermsReference());
		processRosetta(path.newSubPath("cashSettlementTermsReference"), processor, ReferenceWithMetaCashSettlementTerms.class, getCashSettlementTermsReference());
		processRosetta(path.newSubPath("physicalSettlementTermsReference"), processor, ReferenceWithMetaPhysicalSettlementTerms.class, getPhysicalSettlementTermsReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferencePoolItemBuilder extends ReferencePoolItem, RosettaModelObjectBuilder {
		ConstituentWeight.ConstituentWeightBuilder getOrCreateConstituentWeight();
		ConstituentWeight.ConstituentWeightBuilder getConstituentWeight();
		ReferencePair.ReferencePairBuilder getOrCreateReferencePair();
		ReferencePair.ReferencePairBuilder getReferencePair();
		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder getOrCreateProtectionTermsReference();
		ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder getProtectionTermsReference();
		ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder getOrCreateCashSettlementTermsReference();
		ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder getCashSettlementTermsReference();
		ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder getOrCreatePhysicalSettlementTermsReference();
		ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder getPhysicalSettlementTermsReference();
		ReferencePoolItem.ReferencePoolItemBuilder setConstituentWeight(ConstituentWeight constituentWeight);
		ReferencePoolItem.ReferencePoolItemBuilder setReferencePair(ReferencePair referencePair);
		ReferencePoolItem.ReferencePoolItemBuilder setProtectionTermsReference(ReferenceWithMetaProtectionTerms protectionTermsReference0);
		ReferencePoolItem.ReferencePoolItemBuilder setProtectionTermsReferenceValue(ProtectionTerms protectionTermsReference1);
		ReferencePoolItem.ReferencePoolItemBuilder setCashSettlementTermsReference(ReferenceWithMetaCashSettlementTerms cashSettlementTermsReference0);
		ReferencePoolItem.ReferencePoolItemBuilder setCashSettlementTermsReferenceValue(CashSettlementTerms cashSettlementTermsReference1);
		ReferencePoolItem.ReferencePoolItemBuilder setPhysicalSettlementTermsReference(ReferenceWithMetaPhysicalSettlementTerms physicalSettlementTermsReference0);
		ReferencePoolItem.ReferencePoolItemBuilder setPhysicalSettlementTermsReferenceValue(PhysicalSettlementTerms physicalSettlementTermsReference1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("constituentWeight"), processor, ConstituentWeight.ConstituentWeightBuilder.class, getConstituentWeight());
			processRosetta(path.newSubPath("referencePair"), processor, ReferencePair.ReferencePairBuilder.class, getReferencePair());
			processRosetta(path.newSubPath("protectionTermsReference"), processor, ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder.class, getProtectionTermsReference());
			processRosetta(path.newSubPath("cashSettlementTermsReference"), processor, ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder.class, getCashSettlementTermsReference());
			processRosetta(path.newSubPath("physicalSettlementTermsReference"), processor, ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder.class, getPhysicalSettlementTermsReference());
		}
		

		ReferencePoolItem.ReferencePoolItemBuilder prune();
	}

	/*********************** Immutable Implementation of ReferencePoolItem  ***********************/
	class ReferencePoolItemImpl implements ReferencePoolItem {
		private final ConstituentWeight constituentWeight;
		private final ReferencePair referencePair;
		private final ReferenceWithMetaProtectionTerms protectionTermsReference;
		private final ReferenceWithMetaCashSettlementTerms cashSettlementTermsReference;
		private final ReferenceWithMetaPhysicalSettlementTerms physicalSettlementTermsReference;
		
		protected ReferencePoolItemImpl(ReferencePoolItem.ReferencePoolItemBuilder builder) {
			this.constituentWeight = ofNullable(builder.getConstituentWeight()).map(f->f.build()).orElse(null);
			this.referencePair = ofNullable(builder.getReferencePair()).map(f->f.build()).orElse(null);
			this.protectionTermsReference = ofNullable(builder.getProtectionTermsReference()).map(f->f.build()).orElse(null);
			this.cashSettlementTermsReference = ofNullable(builder.getCashSettlementTermsReference()).map(f->f.build()).orElse(null);
			this.physicalSettlementTermsReference = ofNullable(builder.getPhysicalSettlementTermsReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("constituentWeight")
		public ConstituentWeight getConstituentWeight() {
			return constituentWeight;
		}
		
		@Override
		@RosettaAttribute("referencePair")
		public ReferencePair getReferencePair() {
			return referencePair;
		}
		
		@Override
		@RosettaAttribute("protectionTermsReference")
		public ReferenceWithMetaProtectionTerms getProtectionTermsReference() {
			return protectionTermsReference;
		}
		
		@Override
		@RosettaAttribute("cashSettlementTermsReference")
		public ReferenceWithMetaCashSettlementTerms getCashSettlementTermsReference() {
			return cashSettlementTermsReference;
		}
		
		@Override
		@RosettaAttribute("physicalSettlementTermsReference")
		public ReferenceWithMetaPhysicalSettlementTerms getPhysicalSettlementTermsReference() {
			return physicalSettlementTermsReference;
		}
		
		@Override
		public ReferencePoolItem build() {
			return this;
		}
		
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder toBuilder() {
			ReferencePoolItem.ReferencePoolItemBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferencePoolItem.ReferencePoolItemBuilder builder) {
			ofNullable(getConstituentWeight()).ifPresent(builder::setConstituentWeight);
			ofNullable(getReferencePair()).ifPresent(builder::setReferencePair);
			ofNullable(getProtectionTermsReference()).ifPresent(builder::setProtectionTermsReference);
			ofNullable(getCashSettlementTermsReference()).ifPresent(builder::setCashSettlementTermsReference);
			ofNullable(getPhysicalSettlementTermsReference()).ifPresent(builder::setPhysicalSettlementTermsReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferencePoolItem _that = getType().cast(o);
		
			if (!Objects.equals(constituentWeight, _that.getConstituentWeight())) return false;
			if (!Objects.equals(referencePair, _that.getReferencePair())) return false;
			if (!Objects.equals(protectionTermsReference, _that.getProtectionTermsReference())) return false;
			if (!Objects.equals(cashSettlementTermsReference, _that.getCashSettlementTermsReference())) return false;
			if (!Objects.equals(physicalSettlementTermsReference, _that.getPhysicalSettlementTermsReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (constituentWeight != null ? constituentWeight.hashCode() : 0);
			_result = 31 * _result + (referencePair != null ? referencePair.hashCode() : 0);
			_result = 31 * _result + (protectionTermsReference != null ? protectionTermsReference.hashCode() : 0);
			_result = 31 * _result + (cashSettlementTermsReference != null ? cashSettlementTermsReference.hashCode() : 0);
			_result = 31 * _result + (physicalSettlementTermsReference != null ? physicalSettlementTermsReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferencePoolItem {" +
				"constituentWeight=" + this.constituentWeight + ", " +
				"referencePair=" + this.referencePair + ", " +
				"protectionTermsReference=" + this.protectionTermsReference + ", " +
				"cashSettlementTermsReference=" + this.cashSettlementTermsReference + ", " +
				"physicalSettlementTermsReference=" + this.physicalSettlementTermsReference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferencePoolItem  ***********************/
	class ReferencePoolItemBuilderImpl implements ReferencePoolItem.ReferencePoolItemBuilder {
	
		protected ConstituentWeight.ConstituentWeightBuilder constituentWeight;
		protected ReferencePair.ReferencePairBuilder referencePair;
		protected ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder protectionTermsReference;
		protected ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder cashSettlementTermsReference;
		protected ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder physicalSettlementTermsReference;
	
		public ReferencePoolItemBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("constituentWeight")
		public ConstituentWeight.ConstituentWeightBuilder getConstituentWeight() {
			return constituentWeight;
		}
		
		@Override
		public ConstituentWeight.ConstituentWeightBuilder getOrCreateConstituentWeight() {
			ConstituentWeight.ConstituentWeightBuilder result;
			if (constituentWeight!=null) {
				result = constituentWeight;
			}
			else {
				result = constituentWeight = ConstituentWeight.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("referencePair")
		public ReferencePair.ReferencePairBuilder getReferencePair() {
			return referencePair;
		}
		
		@Override
		public ReferencePair.ReferencePairBuilder getOrCreateReferencePair() {
			ReferencePair.ReferencePairBuilder result;
			if (referencePair!=null) {
				result = referencePair;
			}
			else {
				result = referencePair = ReferencePair.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("protectionTermsReference")
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder getProtectionTermsReference() {
			return protectionTermsReference;
		}
		
		@Override
		public ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder getOrCreateProtectionTermsReference() {
			ReferenceWithMetaProtectionTerms.ReferenceWithMetaProtectionTermsBuilder result;
			if (protectionTermsReference!=null) {
				result = protectionTermsReference;
			}
			else {
				result = protectionTermsReference = ReferenceWithMetaProtectionTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("cashSettlementTermsReference")
		public ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder getCashSettlementTermsReference() {
			return cashSettlementTermsReference;
		}
		
		@Override
		public ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder getOrCreateCashSettlementTermsReference() {
			ReferenceWithMetaCashSettlementTerms.ReferenceWithMetaCashSettlementTermsBuilder result;
			if (cashSettlementTermsReference!=null) {
				result = cashSettlementTermsReference;
			}
			else {
				result = cashSettlementTermsReference = ReferenceWithMetaCashSettlementTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("physicalSettlementTermsReference")
		public ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder getPhysicalSettlementTermsReference() {
			return physicalSettlementTermsReference;
		}
		
		@Override
		public ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder getOrCreatePhysicalSettlementTermsReference() {
			ReferenceWithMetaPhysicalSettlementTerms.ReferenceWithMetaPhysicalSettlementTermsBuilder result;
			if (physicalSettlementTermsReference!=null) {
				result = physicalSettlementTermsReference;
			}
			else {
				result = physicalSettlementTermsReference = ReferenceWithMetaPhysicalSettlementTerms.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("constituentWeight")
		public ReferencePoolItem.ReferencePoolItemBuilder setConstituentWeight(ConstituentWeight constituentWeight) {
			this.constituentWeight = constituentWeight==null?null:constituentWeight.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("referencePair")
		public ReferencePoolItem.ReferencePoolItemBuilder setReferencePair(ReferencePair referencePair) {
			this.referencePair = referencePair==null?null:referencePair.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("protectionTermsReference")
		public ReferencePoolItem.ReferencePoolItemBuilder setProtectionTermsReference(ReferenceWithMetaProtectionTerms protectionTermsReference) {
			this.protectionTermsReference = protectionTermsReference==null?null:protectionTermsReference.toBuilder();
			return this;
		}
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder setProtectionTermsReferenceValue(ProtectionTerms protectionTermsReference) {
			this.getOrCreateProtectionTermsReference().setValue(protectionTermsReference);
			return this;
		}
		@Override
		@RosettaAttribute("cashSettlementTermsReference")
		public ReferencePoolItem.ReferencePoolItemBuilder setCashSettlementTermsReference(ReferenceWithMetaCashSettlementTerms cashSettlementTermsReference) {
			this.cashSettlementTermsReference = cashSettlementTermsReference==null?null:cashSettlementTermsReference.toBuilder();
			return this;
		}
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder setCashSettlementTermsReferenceValue(CashSettlementTerms cashSettlementTermsReference) {
			this.getOrCreateCashSettlementTermsReference().setValue(cashSettlementTermsReference);
			return this;
		}
		@Override
		@RosettaAttribute("physicalSettlementTermsReference")
		public ReferencePoolItem.ReferencePoolItemBuilder setPhysicalSettlementTermsReference(ReferenceWithMetaPhysicalSettlementTerms physicalSettlementTermsReference) {
			this.physicalSettlementTermsReference = physicalSettlementTermsReference==null?null:physicalSettlementTermsReference.toBuilder();
			return this;
		}
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder setPhysicalSettlementTermsReferenceValue(PhysicalSettlementTerms physicalSettlementTermsReference) {
			this.getOrCreatePhysicalSettlementTermsReference().setValue(physicalSettlementTermsReference);
			return this;
		}
		
		@Override
		public ReferencePoolItem build() {
			return new ReferencePoolItem.ReferencePoolItemImpl(this);
		}
		
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder prune() {
			if (constituentWeight!=null && !constituentWeight.prune().hasData()) constituentWeight = null;
			if (referencePair!=null && !referencePair.prune().hasData()) referencePair = null;
			if (protectionTermsReference!=null && !protectionTermsReference.prune().hasData()) protectionTermsReference = null;
			if (cashSettlementTermsReference!=null && !cashSettlementTermsReference.prune().hasData()) cashSettlementTermsReference = null;
			if (physicalSettlementTermsReference!=null && !physicalSettlementTermsReference.prune().hasData()) physicalSettlementTermsReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getConstituentWeight()!=null && getConstituentWeight().hasData()) return true;
			if (getReferencePair()!=null && getReferencePair().hasData()) return true;
			if (getProtectionTermsReference()!=null && getProtectionTermsReference().hasData()) return true;
			if (getCashSettlementTermsReference()!=null && getCashSettlementTermsReference().hasData()) return true;
			if (getPhysicalSettlementTermsReference()!=null && getPhysicalSettlementTermsReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferencePoolItem.ReferencePoolItemBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferencePoolItem.ReferencePoolItemBuilder o = (ReferencePoolItem.ReferencePoolItemBuilder) other;
			
			merger.mergeRosetta(getConstituentWeight(), o.getConstituentWeight(), this::setConstituentWeight);
			merger.mergeRosetta(getReferencePair(), o.getReferencePair(), this::setReferencePair);
			merger.mergeRosetta(getProtectionTermsReference(), o.getProtectionTermsReference(), this::setProtectionTermsReference);
			merger.mergeRosetta(getCashSettlementTermsReference(), o.getCashSettlementTermsReference(), this::setCashSettlementTermsReference);
			merger.mergeRosetta(getPhysicalSettlementTermsReference(), o.getPhysicalSettlementTermsReference(), this::setPhysicalSettlementTermsReference);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferencePoolItem _that = getType().cast(o);
		
			if (!Objects.equals(constituentWeight, _that.getConstituentWeight())) return false;
			if (!Objects.equals(referencePair, _that.getReferencePair())) return false;
			if (!Objects.equals(protectionTermsReference, _that.getProtectionTermsReference())) return false;
			if (!Objects.equals(cashSettlementTermsReference, _that.getCashSettlementTermsReference())) return false;
			if (!Objects.equals(physicalSettlementTermsReference, _that.getPhysicalSettlementTermsReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (constituentWeight != null ? constituentWeight.hashCode() : 0);
			_result = 31 * _result + (referencePair != null ? referencePair.hashCode() : 0);
			_result = 31 * _result + (protectionTermsReference != null ? protectionTermsReference.hashCode() : 0);
			_result = 31 * _result + (cashSettlementTermsReference != null ? cashSettlementTermsReference.hashCode() : 0);
			_result = 31 * _result + (physicalSettlementTermsReference != null ? physicalSettlementTermsReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferencePoolItemBuilder {" +
				"constituentWeight=" + this.constituentWeight + ", " +
				"referencePair=" + this.referencePair + ", " +
				"protectionTermsReference=" + this.protectionTermsReference + ", " +
				"cashSettlementTermsReference=" + this.cashSettlementTermsReference + ", " +
				"physicalSettlementTermsReference=" + this.physicalSettlementTermsReference +
			'}';
		}
	}
}
