package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.ConvertibleBond;
import cdm.base.staticdata.asset.common.ConvertibleBond.ConvertibleBondBuilder;
import cdm.base.staticdata.asset.common.ConvertibleBond.ConvertibleBondBuilderImpl;
import cdm.base.staticdata.asset.common.ConvertibleBond.ConvertibleBondImpl;
import cdm.base.staticdata.asset.common.IdentifiedProduct;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductBuilder;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductBuilderImpl;
import cdm.base.staticdata.asset.common.IdentifiedProduct.IdentifiedProductImpl;
import cdm.base.staticdata.asset.common.ProductIdentifier;
import cdm.base.staticdata.asset.common.meta.ConvertibleBondMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;


/**
 * A class to specify a convertible bond as having a product identifier. As a difference versus the FpML standard, the CDM structure of this class only includes the productIdentifier class, which consists of an identifier, productTaxonomy, and source of the identifier. The reason for this approach is to avoid the potential for conflicting information between the information associated with the contractual product and the reference information maintained by the relevant service provider.
 * @version ${project.version}
 */
@RosettaDataType(value="ConvertibleBond", builder=ConvertibleBond.ConvertibleBondBuilderImpl.class, version="${project.version}")
public interface ConvertibleBond extends IdentifiedProduct {

	ConvertibleBondMeta metaData = new ConvertibleBondMeta();

	/*********************** Getter Methods  ***********************/

	/*********************** Build Methods  ***********************/
	ConvertibleBond build();
	
	ConvertibleBond.ConvertibleBondBuilder toBuilder();
	
	static ConvertibleBond.ConvertibleBondBuilder builder() {
		return new ConvertibleBond.ConvertibleBondBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ConvertibleBond> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ConvertibleBond> getType() {
		return ConvertibleBond.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.class, getProductIdentifier());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ConvertibleBondBuilder extends ConvertibleBond, IdentifiedProduct.IdentifiedProductBuilder, RosettaModelObjectBuilder {
		ConvertibleBond.ConvertibleBondBuilder setProductIdentifier(ProductIdentifier productIdentifier);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("productIdentifier"), processor, ProductIdentifier.ProductIdentifierBuilder.class, getProductIdentifier());
		}
		

		ConvertibleBond.ConvertibleBondBuilder prune();
	}

	/*********************** Immutable Implementation of ConvertibleBond  ***********************/
	class ConvertibleBondImpl extends IdentifiedProduct.IdentifiedProductImpl implements ConvertibleBond {
		
		protected ConvertibleBondImpl(ConvertibleBond.ConvertibleBondBuilder builder) {
			super(builder);
		}
		
		@Override
		public ConvertibleBond build() {
			return this;
		}
		
		@Override
		public ConvertibleBond.ConvertibleBondBuilder toBuilder() {
			ConvertibleBond.ConvertibleBondBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ConvertibleBond.ConvertibleBondBuilder builder) {
			super.setBuilderFields(builder);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConvertibleBond {" +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of ConvertibleBond  ***********************/
	class ConvertibleBondBuilderImpl extends IdentifiedProduct.IdentifiedProductBuilderImpl  implements ConvertibleBond.ConvertibleBondBuilder {
	
	
		public ConvertibleBondBuilderImpl() {
		}
	
	
		@Override
		@RosettaAttribute("productIdentifier")
		public ConvertibleBond.ConvertibleBondBuilder setProductIdentifier(ProductIdentifier productIdentifier) {
			this.productIdentifier = productIdentifier==null?null:productIdentifier.toBuilder();
			return this;
		}
		
		@Override
		public ConvertibleBond build() {
			return new ConvertibleBond.ConvertibleBondImpl(this);
		}
		
		@Override
		public ConvertibleBond.ConvertibleBondBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ConvertibleBond.ConvertibleBondBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ConvertibleBond.ConvertibleBondBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			ConvertibleBond.ConvertibleBondBuilder o = (ConvertibleBond.ConvertibleBondBuilder) other;
			
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
		
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			return _result;
		}
		
		@Override
		public String toString() {
			return "ConvertibleBondBuilder {" +
			'}' + " " + super.toString();
		}
	}
}
