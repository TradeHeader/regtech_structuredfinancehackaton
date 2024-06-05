package cdm.event.common;

import cdm.base.staticdata.party.AncillaryParty;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.TermsChangeInstruction.TermsChangeInstructionBuilder;
import cdm.event.common.TermsChangeInstruction.TermsChangeInstructionBuilderImpl;
import cdm.event.common.TermsChangeInstruction.TermsChangeInstructionImpl;
import cdm.event.common.meta.TermsChangeInstructionMeta;
import cdm.product.common.NotionalAdjustmentEnum;
import cdm.product.template.Product;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies instructions for terms change consisting of the new transaction terms, and the renegotiation fee.
 * @version ${project.version}
 */
@RosettaDataType(value="TermsChangeInstruction", builder=TermsChangeInstruction.TermsChangeInstructionBuilderImpl.class, version="${project.version}")
public interface TermsChangeInstruction extends RosettaModelObject {

	TermsChangeInstructionMeta metaData = new TermsChangeInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * product to be changed
	 */
	Product getProduct();
	/**
	 * ancillary party to be changed
	 */
	List<? extends AncillaryParty> getAncillaryParty();
	NotionalAdjustmentEnum getAdjustment();

	/*********************** Build Methods  ***********************/
	TermsChangeInstruction build();
	
	TermsChangeInstruction.TermsChangeInstructionBuilder toBuilder();
	
	static TermsChangeInstruction.TermsChangeInstructionBuilder builder() {
		return new TermsChangeInstruction.TermsChangeInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TermsChangeInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends TermsChangeInstruction> getType() {
		return TermsChangeInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("product"), processor, Product.class, getProduct());
		processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.class, getAncillaryParty());
		processor.processBasic(path.newSubPath("adjustment"), NotionalAdjustmentEnum.class, getAdjustment(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TermsChangeInstructionBuilder extends TermsChangeInstruction, RosettaModelObjectBuilder {
		Product.ProductBuilder getOrCreateProduct();
		Product.ProductBuilder getProduct();
		AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty(int _index);
		List<? extends AncillaryParty.AncillaryPartyBuilder> getAncillaryParty();
		TermsChangeInstruction.TermsChangeInstructionBuilder setProduct(Product product);
		TermsChangeInstruction.TermsChangeInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty0);
		TermsChangeInstruction.TermsChangeInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty1, int _idx);
		TermsChangeInstruction.TermsChangeInstructionBuilder addAncillaryParty(List<? extends AncillaryParty> ancillaryParty2);
		TermsChangeInstruction.TermsChangeInstructionBuilder setAncillaryParty(List<? extends AncillaryParty> ancillaryParty3);
		TermsChangeInstruction.TermsChangeInstructionBuilder setAdjustment(NotionalAdjustmentEnum adjustment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("product"), processor, Product.ProductBuilder.class, getProduct());
			processRosetta(path.newSubPath("ancillaryParty"), processor, AncillaryParty.AncillaryPartyBuilder.class, getAncillaryParty());
			processor.processBasic(path.newSubPath("adjustment"), NotionalAdjustmentEnum.class, getAdjustment(), this);
		}
		

		TermsChangeInstruction.TermsChangeInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of TermsChangeInstruction  ***********************/
	class TermsChangeInstructionImpl implements TermsChangeInstruction {
		private final Product product;
		private final List<? extends AncillaryParty> ancillaryParty;
		private final NotionalAdjustmentEnum adjustment;
		
		protected TermsChangeInstructionImpl(TermsChangeInstruction.TermsChangeInstructionBuilder builder) {
			this.product = ofNullable(builder.getProduct()).map(f->f.build()).orElse(null);
			this.ancillaryParty = ofNullable(builder.getAncillaryParty()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.adjustment = builder.getAdjustment();
		}
		
		@Override
		@RosettaAttribute("product")
		public Product getProduct() {
			return product;
		}
		
		@Override
		@RosettaAttribute("ancillaryParty")
		public List<? extends AncillaryParty> getAncillaryParty() {
			return ancillaryParty;
		}
		
		@Override
		@RosettaAttribute("adjustment")
		public NotionalAdjustmentEnum getAdjustment() {
			return adjustment;
		}
		
		@Override
		public TermsChangeInstruction build() {
			return this;
		}
		
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder toBuilder() {
			TermsChangeInstruction.TermsChangeInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TermsChangeInstruction.TermsChangeInstructionBuilder builder) {
			ofNullable(getProduct()).ifPresent(builder::setProduct);
			ofNullable(getAncillaryParty()).ifPresent(builder::setAncillaryParty);
			ofNullable(getAdjustment()).ifPresent(builder::setAdjustment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TermsChangeInstruction _that = getType().cast(o);
		
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!ListEquals.listEquals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!Objects.equals(adjustment, _that.getAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (adjustment != null ? adjustment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TermsChangeInstruction {" +
				"product=" + this.product + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"adjustment=" + this.adjustment +
			'}';
		}
	}

	/*********************** Builder Implementation of TermsChangeInstruction  ***********************/
	class TermsChangeInstructionBuilderImpl implements TermsChangeInstruction.TermsChangeInstructionBuilder {
	
		protected Product.ProductBuilder product;
		protected List<AncillaryParty.AncillaryPartyBuilder> ancillaryParty = new ArrayList<>();
		protected NotionalAdjustmentEnum adjustment;
	
		public TermsChangeInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("product")
		public Product.ProductBuilder getProduct() {
			return product;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateProduct() {
			Product.ProductBuilder result;
			if (product!=null) {
				result = product;
			}
			else {
				result = product = Product.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("ancillaryParty")
		public List<? extends AncillaryParty.AncillaryPartyBuilder> getAncillaryParty() {
			return ancillaryParty;
		}
		
		public AncillaryParty.AncillaryPartyBuilder getOrCreateAncillaryParty(int _index) {
		
			if (ancillaryParty==null) {
				this.ancillaryParty = new ArrayList<>();
			}
			AncillaryParty.AncillaryPartyBuilder result;
			return getIndex(ancillaryParty, _index, () -> {
						AncillaryParty.AncillaryPartyBuilder newAncillaryParty = AncillaryParty.builder();
						return newAncillaryParty;
					});
		}
		
		@Override
		@RosettaAttribute("adjustment")
		public NotionalAdjustmentEnum getAdjustment() {
			return adjustment;
		}
		
	
		@Override
		@RosettaAttribute("product")
		public TermsChangeInstruction.TermsChangeInstructionBuilder setProduct(Product product) {
			this.product = product==null?null:product.toBuilder();
			return this;
		}
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty) {
			if (ancillaryParty!=null) this.ancillaryParty.add(ancillaryParty.toBuilder());
			return this;
		}
		
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder addAncillaryParty(AncillaryParty ancillaryParty, int _idx) {
			getIndex(this.ancillaryParty, _idx, () -> ancillaryParty.toBuilder());
			return this;
		}
		@Override 
		public TermsChangeInstruction.TermsChangeInstructionBuilder addAncillaryParty(List<? extends AncillaryParty> ancillaryPartys) {
			if (ancillaryPartys != null) {
				for (AncillaryParty toAdd : ancillaryPartys) {
					this.ancillaryParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("ancillaryParty")
		public TermsChangeInstruction.TermsChangeInstructionBuilder setAncillaryParty(List<? extends AncillaryParty> ancillaryPartys) {
			if (ancillaryPartys == null)  {
				this.ancillaryParty = new ArrayList<>();
			}
			else {
				this.ancillaryParty = ancillaryPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("adjustment")
		public TermsChangeInstruction.TermsChangeInstructionBuilder setAdjustment(NotionalAdjustmentEnum adjustment) {
			this.adjustment = adjustment==null?null:adjustment;
			return this;
		}
		
		@Override
		public TermsChangeInstruction build() {
			return new TermsChangeInstruction.TermsChangeInstructionImpl(this);
		}
		
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder prune() {
			if (product!=null && !product.prune().hasData()) product = null;
			ancillaryParty = ancillaryParty.stream().filter(b->b!=null).<AncillaryParty.AncillaryPartyBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getProduct()!=null && getProduct().hasData()) return true;
			if (getAncillaryParty()!=null && getAncillaryParty().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAdjustment()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TermsChangeInstruction.TermsChangeInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			TermsChangeInstruction.TermsChangeInstructionBuilder o = (TermsChangeInstruction.TermsChangeInstructionBuilder) other;
			
			merger.mergeRosetta(getProduct(), o.getProduct(), this::setProduct);
			merger.mergeRosetta(getAncillaryParty(), o.getAncillaryParty(), this::getOrCreateAncillaryParty);
			
			merger.mergeBasic(getAdjustment(), o.getAdjustment(), this::setAdjustment);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			TermsChangeInstruction _that = getType().cast(o);
		
			if (!Objects.equals(product, _that.getProduct())) return false;
			if (!ListEquals.listEquals(ancillaryParty, _that.getAncillaryParty())) return false;
			if (!Objects.equals(adjustment, _that.getAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (product != null ? product.hashCode() : 0);
			_result = 31 * _result + (ancillaryParty != null ? ancillaryParty.hashCode() : 0);
			_result = 31 * _result + (adjustment != null ? adjustment.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TermsChangeInstructionBuilder {" +
				"product=" + this.product + ", " +
				"ancillaryParty=" + this.ancillaryParty + ", " +
				"adjustment=" + this.adjustment +
			'}';
		}
	}
}
